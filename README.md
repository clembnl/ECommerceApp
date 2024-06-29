# E-COMMERCE APP

## RUN APP

1. Build app

```bash
cd api
mvn clean install -DskipTests
```

2. Build Docker container
```bash
cd api/docker
docker compose build
```

3. Run app
```bash
cd api/docker
docker compose up
```

Go to [http://localhost:8080/](http://localhost:8080/)