# api-jersey-mongodb

Simple API to test integration JPA with MONGODB

### INTRODUCTION

**Starting MongoDB**

For ease let's use docker container.

-	Download the image

```
docker pull mongo

```

-	start container 

```
docker run --name my_mongo -d -p 127.0.0.1:27017:27017 mongo:latest

```

Hey, is done, now let's download the *Mongo Compass*, see this page [Document](https://docs.mongodb.com/compass/master/install/#install-on-ubuntu)

With *Mongo Compass* we can manager collections in mongodb instance.

Hey man,very good, phase 1 completed.

### RESOURCES

Exist two resource 

- User
- Post

**User**

To list all users

```
http://localhost:8080/api-jersey-mongodb/service/users
```

---

To get specific user

```
http://localhost:8080/api-jersey-mongodb/service/users/5b761f200cf98c246452ae5c
```

replace `5b761f200cf98c246452ae5c` 

---

to save new user

```
http://localhost:8080/api-jersey-mongodb/service/users
```

in body of request 

```
{
	"name":"CASSUNDE",
	"email":"mattheus@teste.com.br"
}
```
