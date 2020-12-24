# Apache Camel Kafka Integration using Springboot

## Context
    Service which is used to aggregate events from different kafka topics and processed with the help of Apache camel.

### Swagger URL: http://localhost:9000/swagger-ui/
    Bring Application UP and post the message with topic name to /v1/kafka/publish endpoint.
    Topic details are specified in the application.yml file.

####Example 1:
RequestBody: 
{
  "userId": "123",
  "userName": "string"
}
topicName: kafka-demo-test2
    
####Example 2:
RequestBody: 
{
  "userId": "456",
  "userName": "string2"
}
topicName: kafka-demo-test3

###Observation
On the successful attempt of publishing the example1 and example2, we can see the output in the log where camel can consume the message from those 2 topics.
