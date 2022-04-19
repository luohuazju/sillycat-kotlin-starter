### test

```shell
curl -X POST --location "http://localhost:8001" -H "Content-Type: application/json" -d "{ \"text\": \"Hello\" }"

curl -X POST --location "http://localhost:8001" -H "Content-Type: application/json" -d "{ \"text\": \"Bonjor\" }"

curl -X POST --location "http://localhost:8001" -H "Content-Type: application/json" -d "{ \"text\": \"Privet\" }"

curl -X GET --location "http://localhost:8001"
```

### clean the none docker image
```shell
docker rmi $(docker images | grep "<none>" | awk "{print \$3}")
```