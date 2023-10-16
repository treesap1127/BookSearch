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
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.data:spring-data-elasticsearch")
    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
    implementation("org.elasticsearch.client:elasticsearch-rest-high-level-client:7.11.1")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.4")	
    implementation("org.apache.commons:commons-csv:1.10.0")
    implementation("org.apache.commons:commons-lang3:3.0")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.apache.commons:commons-csv:1.8")
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
