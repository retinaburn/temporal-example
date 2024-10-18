# Spring Boot Temporal Example

## Requirements
1. Configure Temporal

    a. Follow the instructions from https://docs.temporal.io/self-hosted-guide/deployment to clone
    
    b. Change the following
    ```
    ports:
      - 8080:8080
    ```
    to:
    ```
    ports:
      - 8082:8080
    ```
    c. Run: docker compose up -d
2. Configure Redpanda

    a. Follow the instreuctions from https://docs.redpanda.com/current/get-started/quick-start/ to clone

    b. Change the following
    ```
    ports:
      - 8080:8080
    ```
    to:
    ```
    ports:
      - 8083:8080
    ```
