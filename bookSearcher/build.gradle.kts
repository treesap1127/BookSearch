plugins {
    id("org.springframework.boot") version "2.5.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("java")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.4")	
    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch:3.1.3")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.apache.commons:commons-csv:1.10.0")
    implementation("org.apache.commons:commons-lang3:3.0")
    runtimeOnly("mysql:mysql-connector-java:8.0.27")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val frontendDir = "$projectDir/src/main/reactfront"

sourceSets {
    main {
        resources {
            srcDirs(listOf("$projectDir/src/main/resources"))
        }
    }
}
