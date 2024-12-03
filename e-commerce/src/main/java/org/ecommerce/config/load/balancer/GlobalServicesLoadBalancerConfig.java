package org.ecommerce.config.load.balancer;

import org.loadbalancer.RandomLoadBalancerConfig;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@LoadBalancerClients(defaultConfiguration = RandomLoadBalancerConfig.class)
public class GlobalServicesLoadBalancerConfig {
}
