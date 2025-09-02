# ZenTarea Platform Hosting Guide

This document describes how to deploy ZenTarea on different platforms with various database backends.

## Supported Platforms

### SQL Databases
- [x] **Azure MSSQL** - Completed (Issue #14)
- [x] **Azure MySQL** - Implemented (Issue #15)

### NoSQL Databases  
- [x] **MongoDB** - Implemented (Issue #16)

## Deployment Options

### 1. Azure MySQL Deployment

#### Prerequisites
- Azure CLI installed and configured
- Java 17 or higher
- Gradle

#### Configuration
The application uses the `azure-mysql` profile for Azure MySQL deployment.

**Environment Variables:**
```bash
SPRING_PROFILES_ACTIVE=azure-mysql
AZURE_MYSQL_URL=jdbc:mysql://your-server.mysql.database.azure.com:3306/zentareadb
AZURE_MYSQL_USERNAME=your-username
AZURE_MYSQL_PASSWORD=your-password
```

#### Quick Deployment
```bash
# Build the application
./gradlew build

# Configure your environment (see .azure/azure-mysql-config.env)
export MYSQL_ADMIN_PASSWORD=your-password

# Deploy to Azure
./.azure/deploy-mysql.sh
```

#### Manual Azure Setup
```bash
# Create resource group
az group create --name zentarea-rg --location eastus

# Create MySQL server
az mysql flexible-server create \
  --resource-group zentarea-rg \
  --name zentarea-mysql-server \
  --admin-user zentareaadmin \
  --admin-password YOUR_PASSWORD

# Create web app
az webapp create \
  --name zentarea-app \
  --resource-group zentarea-rg \
  --plan zentarea-plan \
  --runtime "JAVA|17-java17"
```

### 2. MongoDB Deployment

#### Local Development with Docker
```bash
# Start MongoDB and the application
docker-compose -f docker-compose-mongodb.yml up
```

#### Configuration
The application uses the `mongodb` profile for MongoDB deployment.

**Environment Variables:**
```bash
SPRING_PROFILES_ACTIVE=mongodb
MONGODB_URI=mongodb://localhost:27017/zentarea
MONGODB_DATABASE=zentarea
```

#### Cloud MongoDB (Atlas)
1. Create a MongoDB Atlas cluster
2. Get your connection string
3. Set environment variables:
```bash
export MONGODB_URI=mongodb+srv://username:password@cluster.mongodb.net/zentarea
export SPRING_PROFILES_ACTIVE=mongodb
```

### 3. Local MySQL Development

#### Using Docker Compose
```bash
# Start MySQL and the application
docker-compose -f docker-compose-mysql.yml up
```

#### Manual Setup
```bash
# Start MySQL locally and set profile
export SPRING_PROFILES_ACTIVE=default
./gradlew bootRun
```

## Application Profiles

### Default Profile
- Uses local MySQL database
- Configuration in `application.properties`

### Azure MySQL Profile  
- Uses Azure MySQL Flexible Server
- Configuration in `application-azure-mysql.properties`
- Optimized connection pooling for cloud deployment

### MongoDB Profile
- Uses MongoDB as the primary database
- Configuration in `application-mongodb.properties`
- Disables JPA/Hibernate auto-configuration

## Testing

### Run Tests
```bash
./gradlew test
```

Tests use H2 in-memory database for consistency across environments.

### Profile-Specific Testing
```bash
# Test with specific profile
./gradlew test -Dspring.profiles.active=azure-mysql
```

## Environment Configuration Files

- `application.properties` - Default configuration (local MySQL)
- `application-azure-mysql.properties` - Azure MySQL configuration
- `application-mongodb.properties` - MongoDB configuration
- `application-test.properties` - Test configuration (H2)

## Docker Support

### Building Docker Image
```bash
./gradlew build
docker build -t zentarea:latest .
```

### Available Docker Compose Files
- `docker-compose-mysql.yml` - MySQL + Application
- `docker-compose-mongodb.yml` - MongoDB + Application

## Security Considerations

- Always use environment variables for sensitive configuration
- Enable SSL/TLS for production databases
- Use Azure Key Vault for production secrets
- Configure firewall rules for database access

## Monitoring and Logging

- Application logs are configured in `application-{profile}.properties`
- Use Azure Application Insights for production monitoring
- Database connection health checks are included

## Troubleshooting

### Common Issues

1. **Java Version Mismatch**
   - Ensure Java 17 is installed
   - Check `java -version`

2. **Database Connection Issues**
   - Verify connection strings
   - Check firewall settings
   - Validate credentials

3. **Profile Not Loading**
   - Verify `SPRING_PROFILES_ACTIVE` environment variable
   - Check configuration file names

### Debug Mode
```bash
# Enable debug logging
export LOGGING_LEVEL_ROOT=DEBUG
export SPRING_PROFILES_ACTIVE=your-profile
./gradlew bootRun
```

## Support

For issues related to specific platforms:
- Azure MySQL: Issue #15
- MongoDB: Issue #16
- General deployment: Create a new issue

## Contributing

When adding new platform support:
1. Create new application-{platform}.properties file
2. Add Docker Compose configuration if applicable
3. Update this README
4. Add integration tests
5. Update deployment scripts
