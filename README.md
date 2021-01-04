# chalkling
**Authors:** Jan Garong, Yining Wang, Ruo Ning (Nancy) Qiu

**Server Setup:**
1. Run ``mvn clean install`` to generate a jar file. If this does not work, try running ``./mvnw clean install`` 
   (for MacOS/Linux) or ``mvnw clean install`` on windows.
2. Run ``heroku local web`` to run the jar file on the web locally on your computer.

**Local PostgreSQL Setup**
1. Use brew to install PostgreSQL in the terminal: `brew install postgresql`
2. Once it is installed, run the command: `brew services start postgresql`
3. Go to Intellij Database, add a new PostgreSQL Datasource by clicking the `+` sign, it should automatically add the 
same localhost for you. Click `Test Connection` to ensure it is working.
4. You are ready to go :)

**Testing Setup:**
We are using local PostgreSQL database for testing, ensure you have followed the previous setup.
* For running the main locally, we need to do the following in `java/resources/application.properties`:
   1. Comment out: `spring.datasource.url: ${JDBC_DATABASE_URL:}`
   2. Uncomment: `spring.datasource.url: jdbc:postgresql://localhost:5432/postgres` 
* For testing with the heroku database, we need to hardcode with the url by changing ``application.properties`` 
correspondingly

**Resources used:**
* [java-get-started by heroku](https://github.com/heroku/java-getting-started)
* [RestTemplate Post Request with JSON by baeldung](https://www.baeldung.com/spring-resttemplate-post-json)
* [Configure Datasource in Spring Boot](https://springframework.guru/how-to-configure-multiple-data-sources-in-a-spring-boot-application/)
* [Spring Data JPA Tutorial by Petri Kainulainen](https://www.petrikainulainen.net/spring-data-jpa-tutorial/)
* [JUnit Tests for Spring Data JPA by Nam Ha Minh](https://www.codejava.net/frameworks/spring-boot/junit-tests-for-spring-data-jpa)
* [Self-Contained Testing Using an In-Memory Database by baeldung](https://www.baeldung.com/spring-jpa-test-in-memory-database)
* [How to map a PostgreSQL ARRAY to a Java List with JPA and Hibernate by Vlad Mihalcea](https://vladmihalcea.com/postgresql-array-java-list/)
* [Spring Boot + Session Management Hello World Example - javainuse](https://www.javainuse.com/spring/springboot_session)