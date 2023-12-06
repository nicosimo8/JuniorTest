# JuniorTest API

### Brief description
This is a task managing *API*. You can Create, Read, Update and Delete tasks according to your needs. Mark them as completed or not as you wish.

## Important
- This *API* was designed to run in `Java 17` enviroments.
- This is a *CRUD* simple *RESTful API* with **H2** embedded database. It hasn't persistence.
- See [Entity/Model](#entitymodel) json example to do a HTTP request with `Body`.
- See [Unit test](#unit-test) for more information about.
- **Version:** *1.0.0*
##


### Endpoints
#### GET
```java
/api/tasks
/api/tasks/{status}
/api/task/{id}
```
- {status} is a `Boolean` value.
- {id} is an `Integer` value.

#### POST
```java
/api/task
```
- `status` parameter is set `false` by default
- See [Entity/Model](#entitymodel) .

#### PUT
```java
/api/task/{id}
```
- {id} is an `Integer` value.
- See [Entity/Model](#entitymodel) .

#### DELETE
```java
/api/task/{id}
```
- {id} is an `Integer` value.

## Entity/Model
Body example (json)
```json
{
  "description": "Some text",
  "status": false
}
```
##
### Unit test
The `Unit-test` only covers the `Repository` fuctionality/package
- Save `task`
- Get all `task`
- Get `task` by `id`
- Get `tasks` by `status`
- Update `task` by `id`
- Delete `task` by `id`
##
### Dependencies
- `spring-boot-devtools`
- `spring-boot-starter`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-test`
- `spring-boot-starter-validation` version: `2.3.1.RELEASE`
- `spring-boot-starter-web`
- `hibernate-validator` version: `6.1.5.Final`
- `h2`
- `lombok`
##
*Author:* [Nicolas J. Simonetti](https://github.com/nicosimo8/)