TAG=1.0.0
NAME=sillycat-kotlinstarter

docker-context:

build:
	docker build -f Dockerfile -t $(NAME):$(TAG) .

app-build:
	gradle build

app-run:
	java -Djava.net.preferIPv4Stack=true \
	 -Dspring.profiles.active=dev \
	 -jar ./build/libs/sillycat-kotlin-starter-0.0.1-SNAPSHOT.jar

run:
	docker run -d \
	-p 8001:8001 \
	--link elasticsearch:elasticsearch \
	--link neo4j:neo4j \
	--link redis:redis \
	-e "NEO4J_PASSWORD=neo4jpassword" \
    -e "ELASTIC_HOST=elasticsearch" \
    -e "ELASTIC_PORT=9200" \
    -e "ELASTIC_USERNAME=carl" \
    -e "ELASTIC_PASSWORD=password" \
	--name $(NAME) $(NAME):$(TAG)

run-elasticsearch:
	docker run -d --name elasticsearch \
	-p 9200:9200 -p 9300:9300 \
	-e "discovery.type=single-node" \
	-e ES_JAVA_OPTS="-Xms64m -Xmx512m" \
	elasticsearch:7.17.3

run-kibana:
	docker run -d --name kibana \
	--link elasticsearch:elasticsearch \
	-e ELASTICSEARCH_HOST="http://elasticsearch:9200" \
	-p 5601:5601 \
	kibana:7.17.3

run-neo4j:
	docker run -d --name neo4j \
	-p 7474:7474 -p 7687:7687 \
	-e NEO4J_AUTH=neo4j/neo4jpassword \
	neo4j:3.5.32

run-redis:
	docker run -d --name redis \
	-p 6379:6379 \
	redis:7.0.0-bullseye

run-prod:
	docker run -d -p 8001:9000 --env RUN_ENV=prod --name $(NAME) $(NAME):$(TAG)

clean:
	docker stop $(NAME)
	docker rm $(NAME)

clean-elasticsearch:
	docker stop elasticsearch
	docker rm elasticsearch

clean-kibana:
	docker stop kibana
	docker rm kibana

clean-neo4j:
	docker stop neo4j
	docker rm neo4j

clean-redis:
	docker stop redis
	docker rm redis

