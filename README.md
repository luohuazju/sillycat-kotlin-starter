### test

```shell
curl -X POST --location "http://localhost:8001" -H "Content-Type: application/json" -d "{ \"text\": \"Hello\" }"

curl -X POST --location "http://localhost:8001" -H "Content-Type: application/json" -d "{ \"text\": \"Bonjor\" }"

curl -X POST --location "http://localhost:8001" -H "Content-Type: application/json" -d "{ \"text\": \"Privet\" }"

curl -X GET --location "http://localhost:8001"
```

### how to build on local
```
make app-build
make app-run
```

```shell
export $(cat .env | xargs)
```


### clean the none docker image
```shell
docker rmi $(docker images | grep "<none>" | awk "{print \$3}")
```