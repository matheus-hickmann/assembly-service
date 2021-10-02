READ ME
---

This is an assembly handler project to handle simple voting scenarios.

How do I run this locally?
---

First thing to do is install required software

For database, you can choose between install Postgres locally, or you can install docker, than run `docker-compose up -d` on project root folder.
By doing this you'll have postgres running containerized and do not need to install anything else.

After you have everything installed, you can use the command `./gradlew bootRun` and check if spring-boot starts correctly 