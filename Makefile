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
	docker run -d --name elasticsearch-single -p 9200:9200 -e "discovery.type=single-node" elasticsearch:7.17.3

run-prod:
	docker run -d -p 8001:9000 --env RUN_ENV=prod --name $(NAME) $(NAME):$(TAG)

clean:
	docker stop $(NAME)
	docker rm $(NAME)

clean-elasticsearch:
	docker stop elasticsearch-single
	docker rm elasticsearch-single

