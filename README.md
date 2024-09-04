
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
    DB_USER=<Database User>
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