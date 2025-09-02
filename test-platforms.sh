#!/bin/bash
# Test different platform configurations locally

echo "ZenTarea Platform Testing Script"
echo "==============================="

# Function to test a profile
test_profile() {
    local profile=$1
    echo "Testing profile: $profile"
    
    # Set the profile
    export SPRING_PROFILES_ACTIVE=$profile
    
    # Build the application
    echo "Building application..."
    ./gradlew build -q
    
    if [ $? -eq 0 ]; then
        echo "✅ Build successful for profile: $profile"
    else
        echo "❌ Build failed for profile: $profile"
        return 1
    fi
    
    echo "---"
}

# Test default profile (MySQL)
test_profile "default"

# Test Azure MySQL profile  
test_profile "azure-mysql"

# Test MongoDB profile
test_profile "mongodb"

echo "Platform testing completed!"
echo ""
echo "To run with a specific profile:"
echo "  export SPRING_PROFILES_ACTIVE=azure-mysql"
echo "  ./gradlew bootRun"
echo ""
echo "To deploy with Docker:"
echo "  docker-compose -f docker-compose-mysql.yml up"
echo "  docker-compose -f docker-compose-mongodb.yml up"