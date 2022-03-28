# CLDM-ASSESSMENT

## Build

```
mvn clean package
```

## Docker network creation

```
docker network create assessment_net
```

## Run

```
docker-compose up --build
```

## Things to improve
- Test coverage
- Properties can be moved to Consul and be shared between projects, and can be changed without redeploy
- Add health checks
- Replace field injections with constructor injections
- Add better logging

## Things to note
- Sometimes assessment-service module starts before MySQL and fails because of that