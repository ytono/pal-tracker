
## Get all time entries
```shell script
curl -i localhost:8080/time-entries
```

## Create a time entry
```shell script
curl -i -XPOST -H"Content-Type: application/json" localhost:8080/time-entries -d"{\"projectId\": 1, \"userId\": 2, \"date\": \"2019-01-01\", \"hours\": 8}"
```
## Get a time entry by ID
```shell script
TIME_ENTRY_ID=1
curl -i localhost:8080/time-entries/${TIME_ENTRY_ID}
```

## Update a time entry by ID
```shell script
curl -i -XPUT -H"Content-Type: application/json" localhost:8080/time-entries/${TIME_ENTRY_ID} -d"{\"projectId\": 88, \"userId\": 99, \"date\": \"2019-01-01\", \"hours\": 8}"
```

## Delete a time entry by ID
```shell script
curl -i -XDELETE -H"Content-Type: application/json" localhost:8080/time-entries/${TIME_ENTRY_ID}
```
