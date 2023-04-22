# Project: student-registration-system
A course registration system for university students

# Read Me First

# Getting Started

## Installation

1. [x] JDK (8 and above)
2. [x] React/npm
3. [x] Postgresql
4. [x] Git _(optional)_

## Dependent tools and frameworks used

1. [ ] OracleDB 
2. [ ] Springboot/JDBC
3. [ ] React

## How to run this project?

1. Install JDK from [here](https://www.oracle.com/java/technologies/downloads/ )<br>
   **_Note: You can also install other variants of JDK like OpenJDK/Azul/Red Hat etc. instead of Oracle's_**
2. Install react related dependencies on your system from [here](https://react.dev/learn/start-a-new-react-project).
3. Clone this project
4. Open a terminal or command prompt and change directory to the `student-registration-system` directory

```agsl
cd student-registration-system
```

5. Now build the project, by default this project builds both subproject api and ui where the build artifacts of ui project is copied over to api project's build artifact as a resource. In order to disable UI build and solely build backend then get rid of flag `-Preact` from below commands.
    1. By running below command if you're on a windows based system
   ```agsl
   .\gradlew.bat build -Preact
    ```
    2. Else run below command if you're using unix based system
   ```agsl
   ./gradlew build -Preact
   ```
6. Create a temporary file `jdbc.properties` anywhere on the system/project using [template](jdbc-template.properties) and configure intended database properties to connect with. <br>
Note: If you're running project in an IDE using run configuration then create it at [project's root level](.), since the run configuration will detect it automatically
6. Run the springboot application using below command:

```agsl
java -jar build/libs/srs-api-0.0.1-SNAPSHOT.jar --spring.config.import=jdbc.properties
```
Note: If the above jar doesn't exist, probably the version might have changed, replace with the jar having similar name
pattern that is present on relative path: `build/libs`
7. Alternatively you can use below command and skip the above steps of building project
```agsl
./gradlew bootRun -PjvmArgs="-Dspring.config.import=<PATH>jdbc.properties"
```
Note: Replace <PATH> with the actual absolute path where the `jdbc.properties` file is created.
8. Once the server is up, then you shall find a message similar to this:

```agsl
Started SrsApplication in x.xxx seconds
```