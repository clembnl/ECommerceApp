# E-COMMERCE APP

## RUN APP

1. Backend (locally : run branch prod folder docker-compose, run full app dev branch master folder docker, run full app with cloud branch prod folder docker)

```bash
cd api
mvn clean install -DskipTests
```

2. Build Docker container
```bash
cd api/docker
docker compose build
```

3. Run Backend
```bash
cd api/docker
docker compose up
```

4. Run Frontend

With devServer :
```bash
npm run serve
```

Or go to [http://localhost:8080/](http://localhost:8080/)