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

**Search API**
<br>
User is expected to create configuration which will be treated as input for search. Code Police is using github search APIs internally. We support searching public resources only.
<br>We are limiting the response to most usable information from whatever github is providing. We are not tempering or altering anything at Code Police.

  **Configuration**
<br>
We are expecting user to set unique set of keywords which they think are specific to their project. we have some additional requirements as well if user wants to make use of them like, repository names, filenames & user names. Provided choice to user for scheduling the search or they just want to sea the results right away.

**Scheduler**
<br>
Code Police is using Quartz scheduler to facilitate scheduling of the configurations.
  
