## Synopsis

**Message processing** - Manage sales sent by external companies

## Build Instructions

Install the Maven client (version 3.* or better). Then clone from GIT and then use Maven:
```
$ git clone ...
$ mvn install
```

## Adjusting log level (optionl)

The default log level is "info", case needed it can be changed by the property
```
--logging.level.com.jpmorgan = <level>
```
## Start example
```
java -jar target/message-processing.jar
      --spring.rabbitmq.host=localhost
      --spring.rabbitmq.port=5672
      --spring.rabbitmq.username=guest
      --spring.rabbitmq.password=guest
```
## Test classes

- MessageProcessingApplicationTest
