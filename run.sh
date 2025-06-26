#!/bin/bash

set -e

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m'

VERSION="0.0.1-SNAPSHOT"
DOCKER_HUB_USER="brianvega"
CONTAINER_NAME="orders-db"

echo -e "${GREEN}Building multi-platform images with Docker...${NC}"
echo -e "${YELLOW}Version: ${VERSION}${NC}"

# Enable experimental features for manifests
export DOCKER_CLI_EXPERIMENTAL=enabled

echo -e "${YELLOW}Checking Docker Hub authentication...${NC}"
if ! cat ~/.docker/config.json | grep -q "https://index.docker.io/v1/" 2>/dev/null; then
    echo -e "${RED}❌ Not logged into Docker Hub. Please run: docker login${NC}"
    exit 1
fi

# Services to build
SERVICES=(
    "authorization-server"
    "api-gateway"
    "e-commerce"
    "payment-details-service"
    "billing-information-service"
    "shipping-information-service"
    "spring-cloud-config-server"
)

create_dockerfile() {
    cat > "Dockerfile" << EOF
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy the JAR file
COPY target/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
EOF
}

build_service() {
    local service=$1
    echo -e "${YELLOW}Building ${service} with Docker...${NC}"
    
    if [ -d "$service" ]; then
        cd "$service"
        
        if [ ! -f "Dockerfile" ]; then
            echo -e "${YELLOW}Creating Dockerfile for ${service}...${NC}"
            create_dockerfile "$service"
        fi
        
        if [ ! -d "target" ]; then
            echo -e "${YELLOW}Checking up Database status"
            if docker ps --filter "name=${CONTAINER_NAME}" --filter "status=running" --quiet | grep -q .; then
              echo "Container '${CONTAINER_NAME}' is running."
            else
              echo "Container '${CONTAINER_NAME}' is NOT running."
              echo -e "${GREEN}Setting Up Database"
              docker-compose up database -Vd
              echo -e "${GREEN}✅ Successfully Database set up for ${CONTAINER_NAME}${NC}"
            fi

            echo -e "${YELLOW}Building ${service} with Maven...${NC}"
            mvn clean package #-DskipTests
        fi
        
        echo -e "${GREEN}Building and pushing multi-platform ${service}...${NC}"
        docker buildx create --use --name multiplatform-builder 2>/dev/null || docker buildx use multiplatform-builder
        
        docker buildx build \
            --no-cache \
            --platform linux/amd64,linux/arm64 \
            --tag "${DOCKER_HUB_USER}/${service}:${VERSION}" \
            --tag "${DOCKER_HUB_USER}/${service}:latest" \
            --push \
            .
        
        echo -e "${GREEN}✅ Successfully built multi-platform ${service}${NC}"
        
        cd ..
    else
        echo -e "${RED}❌ Directory ${service} not found${NC}"
    fi
}

# Build all services
for service in "${SERVICES[@]}"; do
    build_service "$service"
    echo ""
done

echo -e "\n\n${GREEN}🎉 Multi-platform Docker build complete!${NC}"
echo -e "${GREEN} Running containers using compose file in dettached mode"
docker-compose up -Vd