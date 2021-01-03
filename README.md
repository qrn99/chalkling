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
* [Configure Datasource in Spring Boot](https://springframework.guru/how-to-configure-multiple-data-sources-in-a-spring-boot-application/)
* [Spring Data JPA Tutorial by Petri Kainulainen](https://www.petrikainulainen.net/spring-data-jpa-tutorial/)
* [JUnit Tests for Spring Data JPA by Nam Ha Minh](https://www.codejava.net/frameworks/spring-boot/junit-tests-for-spring-data-jpa)
* [Self-Contained Testing Using an In-Memory Database by baeldung](https://www.baeldung.com/spring-jpa-test-in-memory-database)
* [Spring Boot + Session Management Hello World Example - javainuse](https://www.javainuse.com/spring/springboot_session)