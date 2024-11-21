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

### AWS Console

1. Create AWS Lambda

```text
Function name: url-shortener
Runtime: Java 17
Additional Configuration > Enable function URL > Auth type = NONE
Hit the button `Create function`!
```
```text
NOTE: update Handler at Runtime Settings based on the package structure of your own project.
```

2. Create an S3 Bucket

```text
Bucket name: url-shortener-bucket (remember that the bucket name must be unique)
```

3. Add Policy Permission

```text
Access Lambda > Configuration > Permissions > Role name > Create policy > S3 > PutObject (Write)
Access Lambda > Configuration > Permissions > Role name > Create policy > S3 > GetObject (Read)
Resources > Specific > Add ARNs:
	. Resource bucket name: url-shortener-bucket
	. Resource object name: *
	. Resource ARN: arn:aws:s3:::url-shortener-bucket/*
Click `Add ARNs`
```

Access JSON View of the Policy Permissions and add not only the ARN bucket objects but also the ARN Bucket permission by doing the following:```

```json
{
	"Version": "2012-10-17",
	"Statement": [
		{
			"Sid": "VisualEditor0",
			"Effect": "Allow",
			"Action": [
				"s3:PutObject",
				"s3:GetObject"
			],
			"Resource": [
    			"arn:aws:s3:::url-shortener-bucket",
    			"arn:aws:s3:::url-shortener-bucket/*"
		    ]
		}
	]
}
```

```text
Policy name: `access-to-url-shortener-bucket`
```


