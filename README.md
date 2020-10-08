# ad-board
Ad board test task

To create database, please, use init-db.sql script in resourses directory.

There is already created admin user (username:admin, password:password).

To register new user, use /register
To authentificate, use /auth

/auth request's response gives you a token, which is mandatory for the rest of the endpoints. Please, use "Authorization" header with value "Bearer " + token
