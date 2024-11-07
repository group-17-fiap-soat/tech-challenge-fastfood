# 🍔 Tech Challenge - FastFood Backend API 🍟

Welcome to the **FastFood Backend Service**! This Kotlin-powered backend is designed to handle all the core functionalities of a FastFood management system. Hungry for some clean code and efficient API magic? Let’s get cooking! 🧑‍💻🔥

---

## 🛠️ Quick Start Guide

This project is fueled by **Docker Compose** to quickly set up your PostgreSQL database environment. Below is a breakdown of the environment variables you’ll need to configure for database connectivity. To get started, create a `.env` file by copying the structure provided in `.env.sample`.

| Variable            | Description                                                    |
|---------------------|----------------------------------------------------------------|
| `DATABASE`          | The database name Docker Compose will create                   |
| `DATABASE_USER`     | The username for PostgreSQL authentication                     |
| `DATABASE_PASSWORD` | Password for your PostgreSQL user                              |
| `DATABASE_PORT`     | Host port for accessing PostgreSQL (default is usually `5432`) |

---

## 🚀 Setup Instructions

1. **Create Your Environment File**

    Duplicate the `.env.sample` file to create your personalized `.env` file:

    ```bash
    cp .env.sample .env
    ```

2. **Update Your Variables**

    Open up `.env` and customize it to your taste (or stick with the sample values!):

    ```plaintext
    DATABASE=db_example
    DATABASE_USER=user_example
    DATABASE_PASSWORD=password_example
    DATABASE_PORT=5432
    ```

3. **Start the Database with Docker Compose**

    Let Docker Compose do the heavy lifting. Fire up PostgreSQL with:

    ```bash
    docker-compose up -d
    ```

4. **Build the Application Docker Image**

    To build the application Docker image, run the following command:

    ```bash
    docker build -t fastfood-app .
    ```

5. **Run the Application Docker Container**

    Once the image is built, run the Spring Boot application in a container:

    ```bash
    docker run -p 8080:8080 fastfood-app
    ```

6. **Check Your Connection**

    PostgreSQL should now be serving up data at `localhost:${DATABASE_PORT}`. Use the credentials in your `.env` to connect. The FastFood application will be accessible at `http://localhost:8080`.

---

## 🔗 Spring Boot Integration

The Spring Boot service will automatically pull in these environment variables to handle the database connection for you. Just make sure that `.env` is in place and configured correctly, then let Spring Boot take care of the rest!

---

## 💡 Pro Tips & Troubleshooting

- **Database Not Connecting?** Double-check that Docker Compose is up and running, and make sure PostgreSQL isn’t already running on the same port outside of Docker.
- **Environment Variables Not Loading?** Ensure your IDE or terminal session recognizes the `.env` file. If not, restart or source the `.env` file manually.
  
With that, you’re all set to start coding, deploying, and managing data like a pro. Welcome to FastFood, where speed meets deliciously clean code. 🍕🍟
