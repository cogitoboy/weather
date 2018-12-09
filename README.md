# Weather Information Service

### Build and Run

Build

```
mvn install
```

Run

```
java -jar target/weatherservice-0.0.1-SNAPSHOT.jar
```

## Use

##### Get Wind Information Guide
Provide a US zipcode via HTTP.GET and retrieve a **Weather** object.

Service
**/api/v1/wind/<zipcode>**

Command line example:
```
curl -X http://localhost:8080/api/v1/wind/89102
```

##### Clear Application Cache
Clear the application cache via HTTP.DELETE.
Service
**/admin/cache**

Command line example
```
curl -X "DELETE"  http://localhost:8080/admin/cache
```
