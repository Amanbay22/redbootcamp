RedBootCamp

## Build instructions

## Code Style
[Google Java Style Guide](https://google.github.io/styleguide/javaguide.html) is applied to the project.

### Checkstyle Gradle Plugin
Run `check` Gradle task to generate HTML report in *build/reports/checkstyle* 

### Intellij IDEA configuration
##### To apply Java style guide to IDEA
1. Go to `Settings` -> `Editor` -> `Code Style` -> `Java`.
2. In the `Scheme` drop-down choose **Project** option. 
3. Then click on :gear: to the right of drop-down and choose `Import Scheme` -> `Intellij IDEA code style XML`
4. Choose *config/checkstyle/intellij-java-google-style.xml*
5. In the dialog window check **Current scheme**
6. Press `OK`

##### To apply IDEA inspection plugin
1. Go to `Settings` -> `Plugins` -> Search for `CheckStyle-IDEA` plugin -> `Install and restart IDE`
2. Go to `Settings` -> `Other Settings` -> `Checkstyle`
3. Add Project related `checkstyle.xml` and set it as Active checkbox
4. Select `Scanning Scope` to `Only Java sources (inluding tests)`
5. Press `OK` 

## Development environment
### Lombok Plugin 

Lombok is used to generate getters for all fields, a useful toString method, and hashCode and equals implementations that check
all non-transient fields. Will also generate setters for all non-final fields, as well as a constructor. 

### Intellij IDEA configuration
1. Go to `Settings` -> `Plugins` -> `Browse Repositories`.
2. In the Search box type `Lombok`. 
3. In search results select `Lombok Plugin`
4. Click `Install`

## Docker Dependencies
Project uses docker based services internally.
Containers should be started prior to application itself. 

1. Postgre DB service - main database to store data
2. Kafka-Zookeper- message broker

### DB migrations
Done by [Flyway](https://flywaydb.org) on application start see `src/main/java/resources/db/migration`
In future more migration, and moments-hibernate-ddl-auto
### starting
* run `docker-compose up -d --build`
* wait some time until DB starts 
* start redbootcampl from IDEA
* Do not forget to do `docker volume prune` after stopping docker compose stack if you need to recreate DB

