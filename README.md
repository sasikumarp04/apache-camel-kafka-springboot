# Apache Camel Kafka Integration using Springboot

## Context
    Service which is used to aggregate events from different kafka topics and processed with the help of Apache camel.

## Swagger URL: http://localhost:9000/swagger-ui/
    Bring Application UP and post the message with topic name to /v1/kafka/publish endpoint.
    Topic details are specified in the application.yml file.

## Steps to create a topic
1. Bring Up the docker-compose.
2. Connect to Confluent-connect using URL http://localhost:9021/
3. Navigate to Cluster --> Topics --> Add a topic(option on right side top). Create a new topic with default settings. 

## Use Cases
### Salary Process Event
RequestBody:
{ 
"employeeNumber": "123", 
"createdDate": "2020-12-30T21:54:54.289642" ,
"status": "IN_PROGRESS",
"currency": "USD"
} 

topicName: salary-process-v1

### Employee Details Event
RequestBody:
{ 
"employeeNumber": "123", 
"createdDate": "2020-12-30T21:54:54.289642" ,
"status": "IN_PROGRESS",
"currency": "USD"
} 

topicName: salary-process-v1

##Observation
Once the message published to kafka successfully you can see the following log message in your service log

### Salary Process Event log
c.kafka.demo.service.KafkaEventConsumer  : Message Output : {"employeeNumber": "123", "createdDate": "2020-12-30T21:54:54.289642", "status": "IN_PROGRESS", "currency": "USD"}
### Employee Details Event log
c.kafka.demo.service.KafkaEventConsumer  : Message Output : {"employeeName": "Test", "employeeNumber": "123", "role": "SoftwareEngineer", "createdDate": "2020-12-30T21:54:54.289642", "updatedDate": "2020-12-30T21:54:54.289642", "mobileNumber": "+910000000000", "emailId": "test@test.com"}
