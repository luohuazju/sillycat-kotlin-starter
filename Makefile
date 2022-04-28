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
	docker run -d -p 8001:8001 -v /etc/hosts:/etc/hosts --name $(NAME) $(NAME):$(TAG)

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

