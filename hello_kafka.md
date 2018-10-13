# Starting the zookeeper and kafka-broker server

<!-- To pre-start a zookeeper server before kafka-->
1. bin/zkServer.sh start
2. bin/zkCli.sh -server 127.0.0.1:2181

<!-- Inside from Kafka Folder: -->
1. start zookeeper server from kafka folder itself
 ```bin/zookeeper-server-start.sh config/zookeeper.properties```

2. start the kafka broker server
 ```bin/kafka-server-start.sh config/server.properties```

3. Create a new topic in the broker server named testtopic
 ```bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic testtopic```

4. List topics
 ```bin/kafka-topics.sh --list --zookeeper localhost:2181```

5. Start console producer
 ```bin/kafka-console-producer.sh --broker-list localhost:9092 --topic testtopic```
6. Start console consumer
 ```bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning```