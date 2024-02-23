# A RESTful API project using Spring framework

### I. Introduction

This project is the Back-end service for a web that is like Stack Overflow, Qoura, Reddit. Technologies used: Spring boot, MySQL. There are several features that are not complete or add such as: calculate contribution, sort type, accepted answer, tags,...

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

**Example for register**
```
curl -X POST http://localhost:8080/register -H 'Content-Type: application/json' -d '{"username": "hongnhat","password": "123"}'
```

**Example for login**
```
curl -X POST http://localhost:8080/login -H"Content-Type: application/json" -d '{"username": "hongnhat","password": "123"}'
```
after request the server will response a token


**Example for view a post with id**
```
curl -H 'Accept: application/json' "http://localhost:8080/post/view/3?page=0&sortType=latest"
```
View a post which has id = 5 and sort the comment by latest

**Example for view all latest post**

```
curl -H 'Accept: application/json' "http://localhost:8080/post/view/all?page=0"
```


**Example for create a new post**
```
curl -X POST http://localhost:8080/post/ask -H"Content-Type: application/json" -H "Authorization: Bearer {YOUR_TOKEN_FROM_LOGIN}" -d '{"title":"How to practice cp","content":"How to practice","user":null}'
```
**Example for create a new comment**
```
curl -X POST http://localhost:8080/comment/reply/5 -H"Content-Type: application/json" -H "Authorization: Bearer {YOUR_TOKEN_FROM_LOGIN}" -d '{"id":null,"upvote":null,"content":"practice more","user":null,"createDate":null,"updateDate":null}'
```
New comment on post which has id = 5

**Example for upvote a post**

```
curl -X PUT 'http://localhost:8080/post/upvote/5?vote=1' -H "Authorization: Bearer {YOUR_TOKEN_FROM_LOGIN}"

```
Upvote a post which has id = 5

**Example for downvote a comment**
```
curl -X PUT 'http://localhost:8080/comment/upvote/1?vote=-1' -H "Authorization: Bearer {YOUR_TOKEN_FROM_LOGIN}"
```
Downvote a comment which has id = 1

**Example for search post by keyword**
```
curl "http://localhost:8080/post/view/search?page=0&keyword=ar&sortType=latest"
```
Search post that contains keyword "ar"