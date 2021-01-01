# chalkling
**Authors:** Jan Garong, Yining Wang, Ruo Ning (Nancy) Qiu

**Server Setup:**
1. Run ``mvn clean install`` to generate a jar file. If this does not work, try running ``./mvnw clean install`` 
   (for MacOS/Linux) or ``mvnw clean install`` on windows.
2. Run ``heroku local web`` to run the jar file on the web locally on your computer.

**Database Setup:**
* For deploying the application online, uncomment line 24-36 in ``PersistenceContext.java`` so spring boot connects to the 
heroku database using environment variable ```${JDBC_DATABASE_URL:}```.
* For running the testing cases, we will be using embedded h2 database by leaving line 24-36 in 
``PersistenceContext.java`` comment out.
* For testing with the heroku database, we need to hardcode with the url by changing ``application.properties`` 
correspondingly.

**Resources used:**
* [java-get-started by heroku](https://github.com/heroku/java-getting-started)
* [RestTemplate Post Request with JSON by baeldung](https://www.baeldung.com/spring-resttemplate-post-json)