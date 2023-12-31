plugins {
    java
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "spring.practice"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_20
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Jetbrains annotation library
    implementation("org.jetbrains:annotations:24.0.0")

    // https://mvnrepository.com/artifact/mysql/mysql-connector-java
    runtimeOnly("mysql:mysql-connector-java:8.0.33")

    // H2 database
    runtimeOnly("com.h2database:h2")

    // https://mvnrepository.com/artifact/org.hibernate/hibernate-core
    implementation("org.hibernate:hibernate-core:6.2.9.Final")

    // Testing Frameworks
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("junit:junit:4.13.1")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
