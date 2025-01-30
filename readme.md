# Message App

This is a simple Spring Boot application that provides a RESTful API for managing messages. It uses a Google Cloud SQL database and is deployed on Google App Engine. Postman is recommended for testing the API endpoints.

---

## Features

- **RESTful Endpoints** for managing messages.
- **Google Cloud SQL** integration for persistent storage.
- **Deployed on Google App Engine** for high availability and scalability.
- **Postman** support for testing API endpoints.

---

## Prerequisites

1. **Google Cloud SDK**
    - Install the [Google Cloud SDK](https://cloud.google.com/sdk/docs/install).
    - Authenticate with your Google account:
      ```bash
      gcloud auth login
      ```
    - Set your project:
      ```bash
      gcloud config set project <PROJECT_ID>
      ```

2. **MySQL Workbench or CLI** (optional)
    - For managing and debugging the Cloud SQL database.

3. **Postman**
    - Install [Postman](https://www.postman.com/) to test the RESTful API.

4. **JDK 17+**
    - Ensure Java 17 or higher is installed.

5. **Maven**
    - Install [Maven](https://maven.apache.org/download.cgi).

---

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/<your-repo>/message-app.git
cd message-app
```

### 2. Update Configuration
Edit the application.properties file located in src/main/resources/ to match your Google Cloud SQL instance details:
```bash
spring.datasource.url=jdbc:mysql:///<DATABASE_NAME>?cloudSqlInstance=<INSTANCE_CONNECTION_NAME>&socketFactory=com.google.cloud.sql.mysql.SocketFactory
spring.datasource.username=<DB_USER>
spring.datasource.password=<DB_PASSWORD>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Replace:
- `<DATABASE_NAME>`: Your database name.
- `<INSTANCE_CONNECTION_NAME>`: Your Cloud SQL instance connection name (e.g., project:region:instance).
- `<DB_USER>` and `<DB_PASSWORD>`: Your database credentials.

### 3. Run Locally
**Start Cloud SQL Proxy**

Ensure the Cloud SQL Proxy is running locally to allow the application to connect to the Cloud SQL database:

```bash
./cloud_sql_proxy -instances=<INSTANCE_CONNECTION_NAME>=tcp:3306
```

**Build and run the application**
```bash
mvn clean package
mvn spring-boot:run
```

Access the API locally at:
- Base URL: http://localhost:8081
- Example Endpoint: http://localhost:8081/messageService/getAllMessages

### 4. Deploy to Google App Engine

**Build the App for Deployment**
```bash
mvn clean package
```

**Deploy the app**
```bash
gcloud app deploy
```

Access the API at:
- Base URL: https://your-project-id.appspot.com
- Example Endpoint: https://your-project-id.appspot.com/messageService/getAllMessages

**API Endpoints**

|Method |Endpoint   |Description    |
|------     | ----- | ----      |   
|GET    |	/messageService/getAllMessages|	Get all messages|
|POST	|/messageService/saveMessage	|Create a new message|
|PUT	|/messageService/updateMessage/{id}	|Update an existing message|
|DELETE	|/messageService/deleteMessage/{id}	|Delete a message|


**Example Request (POST)**
- Endpoint: /messageService/createMessage

```bash
{
  "content": "This is the content of the message."
}
```

## Testing with Postman
- Open Postman.
- Create a new request and set the method (e.g., GET, POST).
- Enter the API URL (e.g., https://<your-project-id>.appspot.com/messageService/getAllMessages).
- Add a JSON body for POST/PUT requests.
- Send the request and inspect the response.
