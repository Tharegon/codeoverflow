# codeoverflow
This is a Spring based backend project with [PSQL](http://postgresguide.com/utilities/psql.html).This is a backend to a Stackoverflow like web page where we use Spring, Spring security. [Here](https://github.com/Tharegon/codeoverflow-React) is the frontend.

How to setup:<br>
First you need a [database](https://www.postgresql.org/docs/9.1/app-createdb.html) on your device.<br>
```bash 
createdb mydb
```
<br>

Second [connect](https://www.jetbrains.com/help/idea/database-tool-window.html) with your IDE we useing intelliJ IDEA
- Database<br>
- new data source + <br>
- PSQL<br>
- Then [setup](https://www.jetbrains.com/help/idea/connecting-to-a-database.html#connect-to-cassandra-database) the variables<br>

Third setup [environment variables](https://www.jetbrains.com/help/objc/add-environment-variables-and-program-arguments.html) in your IDE, you will need these variables:
- PSQL_DB_HOST : your host
- PSQL_DB_PORT  : something like 8888
- PSQL_DB_NAME  : name of your database ex.: mydb
- PSQL_DB_USERNAME : your PSQL user name
- PSQL_DB_PASSWORD : your PSQL password
