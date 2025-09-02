# ZenTarea - Task Management Application

ZenTarea is a Spring Boot application for task management with support for multiple database backends.

## Database Support

### MySQL (Default)
The application uses MySQL as the default database. Configuration is in `application.properties`.

### Amazon Redshift
Redshift support has been added for analytics and data warehousing use cases.

#### Using Redshift

1. **Activate the Redshift profile:**
   ```bash
   java -jar zentarea.jar --spring.profiles.active=redshift
   ```

2. **Configure Redshift connection in `application-redshift.properties`:**
   ```properties
   spring.datasource.url=jdbc:redshift://your-cluster.region.redshift.amazonaws.com:5439/your-database
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   ```

3. **Dependencies included:**
   - Amazon Redshift JDBC driver (2.1.0.24)
   - PostgreSQL dialect for Hibernate (Redshift is PostgreSQL-compatible)

#### Analytics Features

The application includes analytics capabilities suitable for Redshift:

- **TaskAnalytics model:** Stores aggregated task data for reporting
- **Analytics endpoints:** RESTful APIs for querying task analytics
- **Repository methods:** Optimized queries for analytics use cases

#### Analytics API Endpoints

- `POST /analytics` - Store task analytics data
- `GET /analytics/status/{status}` - Get tasks by status
- `GET /analytics/priority/{priority}` - Get tasks by priority
- `GET /analytics/completed?startDate={date}&endDate={date}` - Tasks completed in date range
- `GET /analytics/slow-tasks/{days}` - Tasks taking more than specified days
- `GET /analytics/avg-completion-time` - Average completion time by priority
- `GET /analytics/status-counts` - Task count by status

## Development

### Building
```bash
./gradlew build
```

### Running Tests
```bash
./gradlew test
```

### Running the Application
```bash
./gradlew bootRun
```

Or with Redshift:
```bash
./gradlew bootRun --args='--spring.profiles.active=redshift'
```