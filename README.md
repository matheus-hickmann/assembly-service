READ ME
---

This is an assembly handler project to handle simple voting scenarios.

How do I run this locally?
---

First thing to do is install required software

For database, you can choose between install Postgres locally, or you can install docker, than run `docker-compose up -d` on project root folder.
By doing this you'll have postgres running containerized and do not need to install anything else.

You need to create `aeemby_db` database. It could be automized by a terraform script. But for this POC it's not at MVP.

After you have everything installed, you can use the command `./gradlew bootRun` and check if spring-boot starts correctly 

API Docs
---
You can access the OpenAPI [here](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config) after project is running.

My Considerations
---

Tried to use S.O.L.I.D and KISS principles at this projects.
Hadn't time to complete my tests, but made a couple of them to show how would I do.
There is a couple of scenarios that I didn't covered, as a invalid document enter, also wanted to have a mutation test and better documentation of API.
But my main intention here is to show how I would implement a simple solution. Took near to 10h to make this project.