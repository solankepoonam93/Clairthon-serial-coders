# clairthon-serial-coders

**Database Prerequisites**
<li>Please make sure you have mysql installed on your machine and the service is running.
<li>Start mysql client from console.
<li>Create database using this SQL query: "create database myDB;"
<li>In resources/META-INF/persistence.xml, provide valid database credentials.
<li>Tables will be created automatically when tests are executed.

<br><br>
**Executing jar file**
<li>Set the AUTH_TOKEN as environment variables</li>
<li>In intellij, you can configure environment variables from -> Edit Configuration</li>
<li>Create jar file using: mvn clean compile install</li>
<li>It will create jar file and also creates Code coverage report</li>

<li>Running unit tests</li>
<li>Run mvn test to execute the unit tests <li>
