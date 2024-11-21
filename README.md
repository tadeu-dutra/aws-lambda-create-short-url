# URL Shortener with AWS Lambda

This project is a URL shortener application developed during a Java course, leveraging AWS Lambda to create a serverless architecture. The application allows users to create short URLs and redirect them to the original URLs.

## Keywords

Serverless, AWS Lambda, AWS S3, API Gateway

## Overview

This repository is responsible for the **creation of short URLs**.

### Prerequisites

- **Java**: Version 17
- **Maven**: Ensure Maven is installed on your machine.

### Create a New Project

1. Create a new Maven project:

```bash
mvn archetype:generate -DgroupId=com.serverless -DartifactId=aws-lambda-create-short-url -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

2. Update the pom.xml to use Java 17:

```xml
<properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
</properties>
```

3. Build and run the project:

```bash
mvn clean install
mvn exec:java -Dexec.mainClass="com.example.App"
```

4. Add the following dependencies to your pom.xml:

```xml
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>3.8.1</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.34</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>com.amazonaws</groupId>
        <artifactId>aws-lambda-java-core</artifactId>
        <version>1.2.1</version>
    </dependency>
    <dependency>
        <groupId>com.amazonaws</groupId>
        <artifactId>aws-lambda-java-log4j2</artifactId>
        <version>1.4.0</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.12.3</version>
    </dependency>
    <dependency>
        <groupId>software.amazon.awssdk</groupId>
        <artifactId>s3</artifactId>
        <version>2.17.106</version>
    </dependency>
</dependencies>
```

5. Add the plugin maven-shade-plugin, used to create a "fat JAR."

```xml
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
        <version>3.2.4</version>
        <executions>
          <execution>
            <phase>package</phase>
            <goals>
              <goal>shade</goal>
            </goals>
            <configuration>
              <createDepenpencyReducedPom>false</createDepenpencyReducedPom>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
```

6. Build the package

```bash
mvn clean package
```


