# Starting the zookeeper and kafka-broker server

<!-- To pre-start a zookeeper server before kafka-->
1. bin/zkServer.sh start
2. bin/zkCli.sh -server 127.0.0.1:2181

<!-- start zookeeper server from kafka folder itself -->
1. bin/zookeeper-server-start.sh config/zookeeper.properties
 

<!-- start the kafka broker server -->
2. bin/kafka-server-start.sh config/server.properties

<!-- Create a new topic in the broker server named testtopic -->
 3. bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic testtopic

<!-- List topics -->
 4. bin/kafka-topics.sh --list --zookeeper localhost:2181

<!-- 5. Start console producer -->
 5. bin/kafka-console-producer.sh --broker-list localhost:9092 --topic testtopic

<!-- Start console consumer  -->
 6. bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning