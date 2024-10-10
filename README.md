
# Local Database Setup task
This task involves creating a Docker Compose manifest to set up a database container using the latest PostgreSQL image.

## Prerequisites

- Docker
- Docker Compose

## Setup Instructions

1. **Clone the Repository**

   ```bash
   git clone https://github.com/algutierrez-griddynamics/e-commerce.git
   cd e-commerce
   ```
2. **Create Environment Variables File**  
Create a .env file in the root directory with the following content:
  
    ```bash
   DB_URL=<Database URL>
   DB_USER=<Database user>
   DB_PASSWORD=<Database password>
   DB_NAME=<Database name>
   DB_PORT=5432
    ```
3. **Start the Database Container**  
Run Docker Compose to start the database container:

    ```bash
    docker-compose up -d
    ```

4. **Connect to the Database**
<img width="795" alt="image" src="https://github.com/user-attachments/assets/f6939504-65fd-43b1-a3a7-2462834e6d07">


**Persistent Data**  
The database data is persisted using Docker volumes, so it remains intact even if the container is recreated.  
You can find the volume named db_data which is used to store the database files.

**Tables creation**  
The ```init_db.sql``` file located in the ```./db``` directory is mounted to ```/docker-entrypoint-initdb.d``` 
in the container. This setup ensures that tables are created and any previous data is reloaded when the container is initialized.
![img.png](https://github.com/user-attachments/assets/461f4cdb-e507-465e-b355-c2721095fae2)

## Environments
There are some `.properties` files included in the project, these file included some configurations that enables certain profile environments
### application.properties
```properties
spring.profiles.active=dev
```
This file indicates to Spring which profile to choose when executing the application, in this case, the `dev` profile has been selected.

### application-local.properties
This `.properties` file is the one in charge of provide an in-memory database for test all the application features but in a local environment without the needing of an external setup.
```properties
gd.datasource.url=jdbc:h2:mem:localdb
gd.datasource.username=sa
gd.datasource.password=password
gd.datasource.driverClassName=org.h2.Driver
gd.jpa.hibernate.ddl-auto=create
```
This configuration sets up an H2 database in memory, and the schema will be created automatically upon application startup (`ddl-auto=create`).
### application-dev.properties
This `.properties` file is the one in charge of provide the development database. In order to connect to such database is mandatory to define the `URL`, `USERNAME` and `PASSWORD`.
```properties
gd.datasource.url=${DB_URL}
gd.datasource.username=${DB_USER}
gd.datasource.password=${DB_PASSWORD}
```
We can define a `.env` file with all the necessary data to successfully connect to the database instance
```properties
DB_URL=DB_URL
DB_USER=DB_USER
DB_PASSWORD=DB_PASSWORD
```
then we can load all those environment variables before running the application
```bash
source .env
```
### Running the Application
Once the environment variables are set, we can run the application using our preferred method. Spring will automatically use the development profile's properties.
