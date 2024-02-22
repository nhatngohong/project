# A RESTful API project using Spring framework

### I. Introduction

This project is the BE service for a web that is like Overflow. Technologies used: Spring frameword, MySQL. There are several features that are not complete or add such as: calculate contribution, sort type, accepted answer, tags,...

### II. Features
#### Complete
- Register
- Post a question
- Comment in a post
- Upvote a post
- Upvote a comment
- Search post by keyword
#### In progress
- Add elasticsearch to improve searching performance
- Add tags to post
- Search by tags
- Accepted answer


### III. Installation

1. Clone the repo
```
https://github.com/nhatngohong/project.git
```

2. Go to project folder
3. Run docker compose
```
docker compose up
```
4. The backend is ready on `localhost:8080` Can check if the service is up by command
```
curl http://localhost:8080 
```


### IV. How To Use

Example for register
```
curl -X POST http://localhost:8080/register -H 'Content-Type: application/json' -d '{"username": "hongnhat","password": "123"}'
```
**Example for login**
```
curl -X POST http://localhost:8080/login -H"Content-Type: application/json" -d '{"username": "hongnhat","password": "123"}'
```
after request the server will response a token


**Example for a request required authentication**
```
curl -H 'Accept: application/json' -H "Authorization: Bearer {YOUR_TOKEN}" "http://localhost:8080/post/view/3?page=0&sortType=latest"
```

view a post with id
```
curl -H 'Accept: application/json' -H "Authorization: Bearer {YOUR_TOKEN}"  "http://localhost:8080/post/view/all?page=0"
```
view all latest post

