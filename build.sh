#mvn clean compile
docker build -t backend .
docker rm -f app db
docker-compose up