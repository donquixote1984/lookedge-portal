curl -X POST localhost:9999/shutdown
mvn package
nohup java -jar target/lookedge-1.0-SNAPSHOT.jar > /dev/null 2>&1 &
