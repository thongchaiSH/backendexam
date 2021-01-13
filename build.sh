#mvn clean compile package -Dmaven.test.skip=true
#mv target/engine-0.0.1-SNAPSHOT.jar build/
docker build -t backend .
docker rm -f app db
docker-compose up