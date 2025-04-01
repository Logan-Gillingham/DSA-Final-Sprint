# Binary Search Tree Application

This Spring Boot application allows users to create binary search trees from input numbers, visualize them, and view previously generated trees.

## Prerequisites

* Java 17 or later
* Maven (or Maven Wrapper included)
* A web browser

## How to Run the Application

1.  **Clone the Repository (if applicable):**

    If you cloned this project from a repository, navigate to the project's root directory in your terminal or command prompt.

2.  **Build and Run the Application:**

    * **Using Maven Wrapper (Recommended):**

        ```bash
        ./mvnw spring-boot:run
        ```

        (On Windows, use `mvnw spring-boot:run`)

    * **Using Maven (if installed):**

        ```bash
        mvn spring-boot:run
        ```

    This command will build the application and start the embedded Tomcat server. You should see output in the console indicating that the application has started successfully.

3.  **Access the Application in Your Web Browser:**

    Once the application is running, open your web browser and navigate to the following URLs:

    * **Enter Numbers:**

        `http://localhost:8080/enter-numbers`

        This page provides an input form where you can enter a comma-separated list of numbers to create a binary search tree.

    * **View Previous Trees:**

        `http://localhost:8080/previous-trees`

        This page displays a list of previously generated trees, showing the input numbers and the corresponding tree structure.

    * **H2 Console (Optional, for development):**

        `http://localhost:8080/h2-console`

        This page is a web-based database console. It allows you to check the data stored in the database. You will need to use the following settings to connect:

        * **JDBC URL:** `jdbc:h2:mem:testdb`
        * **User Name:** `sa`
        * **Password:** (Leave blank)

## Using the Application

1.  **Enter Numbers:**

    * Go to `http://localhost:8080/enter-numbers`.
    * Enter a comma-separated list of numbers in the input field (e.g., `5,3,7,2,4,6,8`).
    * Click "Submit" to create the binary search tree or "Submit Balanced" to create a balanced binary search tree.
    * The tree structure will be returned as a JSON response.

2.  **View Previous Trees:**

    * Go to `http://localhost:8080/previous-trees`.
    * You will see a list of previously generated trees. Each entry shows the input numbers and the JSON representation of the tree.

## Application Details

* The application uses an in-memory H2 database to store the tree data. This means that the data will be lost when the application stops.
* The application uses Thymeleaf for server-side HTML rendering.
* The application uses Jackson for JSON serialization and deserialization.
* The application includes a bonus feature to create a balanced binary search tree when the user clicks the submit balanced button.
* The application includes unit tests.

## Development Notes

* Ensure all dependencies are correctly downloaded by Maven.
* If you encounter "not on classpath" errors, try reloading your Maven project in your IDE.
* If you encounter "javax does not exist" errors, ensure you are using a spring boot version 3 or later, and that all javax imports have been changed to jakarta imports.
