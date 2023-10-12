|                       Topic                       |                                       How To ?                                        |
|:-------------------------------------------------:|:-------------------------------------------------------------------------------------:|
| Reactive/Event Driven Consuming Events with Kafka |                             See details below this table                              |
|    Reactive/Event Driven Hot and Cold Streams     | [Readme](./autohire-consumer/src/test/java/com/carrental/autohire/consumer/Readme.md) |
|    Kafka Hot and Cold consumer                    | [Readme](./autohire-kakfa-cold-consumer/Readme.md) |


# Setting Up a Spring Boot Application
This guide will walk you through the steps to set up a Spring Boot application.

# Prerequisites
Before starting the setup process, ensure you have the following:

- Git installed on your machine

- Docker installed in your machine. Docker Desktop installation will include docker and docker-compose

- Java Development Kit (JDK) installed

# Setup Steps

## Clone the repository:

Run the following command in your terminal:

git clone <repository_url>

# Step 1 : To run kafka broker and zookeeper


## Direct Installation
1. Download Zookeeper and Kafka.
2. Install as per the instruction given https://kafka.apache.org/quickstart
3. Start both Zookeeper and Kafka seperately.
4. Create log folder for kafka and zookeeper
5. Configure the folders in the config files.
6. Start kafka and zookeeper seperately.

### To Start Zookeeper

Open new command prompt. Goto Kafka Installed folder

.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

### To Start the Kafka Server

Open new command prompt. Goto Kafka Installed folder

.\bin\windows\kafka-server-start.bat .\config\server.properties

![Start Zookeeper and Kafka](./media/runningKafka.gif)

## Docker Compose

1. Goto APP_HOME ie advance-java-repo-internal
2. find a docker-compose.yml file there
3. Run the command 
  
        docker-compose up
![Start Zookeeper and Kafka using docker compose](./media/zookeeper-kafka-docker-compose.gif)


# Step 2 : Run the Spring Boot application:

### Using mvn
Open the Spring Boot project in your Visual Studio Code.
1. ./mvnw clean package

#### Under autohire-producer

1. cd autohire-producer
2. Locate the AutohireProducerApplication class and run it.
3. This will start the Spring Boot application and initialize an embedded Tomcat server.
4. Alternatively, you can run the application using the following command in the terminal:

**java -jar ./target/autohire-producer-0.0.1-SNAPSHOT.jar**

#### Under autohire-consumer
1. cd autohire-consumer
2. Locate the AutohireConsumerApplication class and run it.
3. This will start the Spring Boot application and initialize an embedded Tomcat server.
4. Alternatively, you can run the application using the following command in the terminal:

**java -jar ./target/autohire-consumer-0.0.1-SNAPSHOT.jar**

![Running Producer and Consumer Application](./media/RunningProducerConsumer.gif)

## Docker
#### to build image for autohire-producer

docker build --tag car-auto-hire-producer:local .

#### to run image

docker run --name car-auto-hire -p 8080:8080 car-auto-hire-producer:local

#### to build image for autohire-consumer

docker build --tag car-auto-hire-consumer:local .

#### to run image

docker run --name car-auto-hire -p 8081:8081 car-auto-hire-consumer:local

### Step 3 : Access the Swagger UI:

Open a web browser and navigate to 
For sending message to producer and inserting the values
1. http://localhost:8080/swagger-ui/index.html

For reading the objects
2. http://localhost:8081/swagger-ui/index.html 


This will open the Swagger UI, where you can explore and interact with the application's APIs.

### Step 4 : Sending Message in Kafka

1. using swagger `sayHello` api give any name.
2. Check a greeting is displayed in the consumer console.
![Sending Message and Consuming](./media/SendingMessage.gif)

Note: If port 8080 is already in use on your machine, you can change the port in the application.properties file located in the project's /src/main/resources directory.

FAQ

please put you question here so that I can give answer.

