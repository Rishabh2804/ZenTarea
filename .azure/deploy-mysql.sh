#!/bin/bash
# Azure MySQL Deployment Script for ZenTarea

# Load configuration
source .azure/azure-mysql-config.env

echo "Deploying ZenTarea to Azure with MySQL..."

# Create Resource Group
az group create --name $RESOURCE_GROUP --location $LOCATION

# Create MySQL Server
az mysql flexible-server create \
  --resource-group $RESOURCE_GROUP \
  --name $MYSQL_SERVER_NAME \
  --location $LOCATION \
  --admin-user $MYSQL_ADMIN_USER \
  --admin-password $MYSQL_ADMIN_PASSWORD \
  --sku-name Standard_B1ms \
  --tier Burstable \
  --public-access 0.0.0.0 \
  --storage-size 20 \
  --version 8.0.21

# Create Database
az mysql flexible-server db create \
  --resource-group $RESOURCE_GROUP \
  --server-name $MYSQL_SERVER_NAME \
  --database-name $MYSQL_DATABASE_NAME

# Create App Service Plan
az appservice plan create \
  --name $APP_SERVICE_PLAN \
  --resource-group $RESOURCE_GROUP \
  --location $LOCATION \
  --sku $SKU \
  --is-linux

# Create Web App
az webapp create \
  --name $WEB_APP_NAME \
  --resource-group $RESOURCE_GROUP \
  --plan $APP_SERVICE_PLAN \
  --runtime $RUNTIME

# Configure App Settings
az webapp config appsettings set \
  --name $WEB_APP_NAME \
  --resource-group $RESOURCE_GROUP \
  --settings \
    SPRING_PROFILES_ACTIVE=$SPRING_PROFILES_ACTIVE \
    AZURE_MYSQL_URL=$AZURE_MYSQL_URL \
    AZURE_MYSQL_USERNAME=$AZURE_MYSQL_USERNAME \
    AZURE_MYSQL_PASSWORD=$MYSQL_ADMIN_PASSWORD

# Deploy the JAR file (assumes it's been built)
az webapp deploy \
  --name $WEB_APP_NAME \
  --resource-group $RESOURCE_GROUP \
  --src-path build/libs/ZenTarea-0.0.1-SNAPSHOT.jar \
  --type jar

echo "Deployment completed! Your app should be available at: https://$WEB_APP_NAME.azurewebsites.net"