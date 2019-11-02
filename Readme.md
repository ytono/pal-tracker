# Spring MVC with REST Endpoints

URL=http://localhost:8080
URL=https://pal-tracker-yt.apps.evans.pal.pivotal.io
TIME_ENTRY_ID=1

## Get all time entries
```shell script
curl -i ${URL}/time-entries
```

## Create a time entry
```shell script
curl -i -XPOST -H"Content-Type: application/json" ${URL}/time-entries -d"{\"projectId\": 1, \"userId\": 2, \"date\": \"2019-01-01\", \"hours\": 8}"
```
## Get a time entry by ID
```shell script
curl -i ${URL}/time-entries/${TIME_ENTRY_ID}
```

## Update a time entry by ID
```shell script
curl -i -XPUT -H"Content-Type: application/json" ${URL}/time-entries/${TIME_ENTRY_ID} -d"{\"projectId\": 88, \"userId\": 99, \"date\": \"2019-01-01\", \"hours\": 8}"
```

## Delete a time entry by ID
```shell script
curl -i -XDELETE -H"Content-Type: application/json" ${URL}/time-entries/${TIME_ENTRY_ID}
```


# Cloud Foundry Services & Database Migrations
