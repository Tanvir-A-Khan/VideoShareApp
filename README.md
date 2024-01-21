# VideoShareApp API Documentation

Welcome to the API documentation for VideoShareApp, a video sharing application backend. This API provides endpoints to manage users and videos, including features such as user authentication, video upload, and interaction with videos.


**Brief project description goes here.**

## Getting Started

Ready to dive in? Here's how to set up the project on your local machine:

### Prerequisites

Before we begin, make sure you have these tools installed:

- **Git:** Get it here: [https://git-scm.com/](https://git-scm.com/)
- **Java Development Kit (JDK):** Download from Oracle: [https://www.oracle.com/java/technologies/javase-downloads.html](https://www.oracle.com/java/technologies/javase-downloads.html)
- **Maven:** Grab it from Apache: [https://maven.apache.org/](https://maven.apache.org/)
- **Node.js:** For running the frontend, get it from [https://nodejs.org/](https://nodejs.org/)

### Clone the Repository

1. **Copy the repository URL:** Click the green "Code" button on the top right of this GitHub page and copy the HTTPS link.

2. **Open your terminal:** Fire up your command line or terminal app.

3. **Clone the repository:** Type the following command, replacing the placeholder URL with the one you copied:

```bash
git clone - https://github.com/Tanvir-A-Khan/VideoShareApp.git


### Set Up the Backend

1. **Open in IntelliJ IDEA:** Launch IntelliJ IDEA and open the newly cloned project.

2. **Locate the main file:** Navigate to `src/main/java/com/example/VideoShareApp/VideoShareAppApplication.java`.

3. **Run the application:** Click the "Run" button (green triangle) to start the backend.

### Set Up the Frontend

1. **Enter the frontend directory:** In your terminal, switch to the frontend directory:

bash
cd VideoShareApp/frontend
npm i
npm run dev


# API Documentation: User Service

## Overview

The User Service API provides endpoints for managing user-related operations, including retrieving user information, adding new users, and user login.

## Base URL
http://localhost:8080/v1/

## Endpoints

### 1. Get Users

- **Endpoint**: `GET /user`
- **Description**: Retrieve a list of all users.
- **Response**:
  - 200 OK: List of users successfully retrieved.
  - Example Response:
    ```json
    [
      {
        "userId": 1,
        "username": "example_user",
        "email": "user@example.com"
      },
      // Additional users...
    ]
    ```
    ## Sample Requests
    GET /user

### 2. Add User

- **Endpoint**: `POST /user`
- **Description**: Add a new user.
- **Request**:
  - Method: `POST`
  - Body: JSON object representing the new user.
    ```json
    {
      "username": "new_user",
      "email": "newuser@example.com",
      "password": "password123"
    }
    ```
- **Response**:
  - 200 OK: User successfully added.
  - Example Response:
    ```json
    [
      {
        "userId": 1,
        "username": "example_user",
        "email": "user@example.com"
      },
      // Additional users including the newly added one...
    ]
    ```
    ## Sample Requests
      POST /user
      Content-Type: application/json
      
      {
        "username": "new_user",
        "email": "newuser@example.com",
        "password": "password123"
      }

### 3. User Login

- **Endpoint**: `GET /login`
- **Description**: Find a user by email and password for login purposes.
- **Parameters**:
  - `email` (String): User's email.
  - `password` (String): User's password.
- **Response**:
  - 200 OK: User found. Returns user information.
  - 404 Not Found: User not found.
  - Example Response (200 OK):
    ```json
    {
      "userId": 1,
      "username": "example_user",
      "email": "user@example.com"
    }
    ```
    ## Sample Requests
    GET /login?email=user@example.com&password=password123

## Error Handling

- 400 Bad Request: Invalid request or missing parameters.
- 404 Not Found: User not found.
- 500 Internal Server Error: Unexpected server error.

## Cross-Origin Resource Sharing (CORS)

- Cross-Origin Resource Sharing (CORS) is enabled for the following origin:
- @CrossOrigin(origins = "http://localhost:5173/")


## Authentication [Not handled]

- No authentication is required for the provided endpoints.


# API Documentation: Video Service

## Overview

The Video Service API provides endpoints for managing video-related operations, including adding videos, retrieving all videos, getting a video by ID, adding likes, toggling dislikes, and retrieving videos by uploader name.

## Base URL

http://localhost:8080/v1/


## Endpoints

### 1. Add a Video

- **Endpoint**: `POST /add-a-video`
- **Description**: Add a new video.
- **Request**:
  - Method: `POST`
  - Body: JSON object representing the new video.
    ```json
    {
      "title": "Sample Video",
      "url": "https://example.com/sample-video",
      "uploaderName": "john_doe"
    }
    ```
- **Response**:
  - 201 Created: Video successfully added.
  - Example Response:
    ```json
    {
      "videoId": "abc123",
      "title": "Sample Video",
      "url": "https://example.com/sample-video",
      "uploaderName": "john_doe"
    }
    ```

### 2. Get All Videos

- **Endpoint**: `GET /get-all-videos`
- **Description**: Retrieve a list of all videos.
- **Response**:
  - 200 OK: List of videos successfully retrieved.
  - Example Response:
    ```json
    [
      {
        "videoId": "abc123",
        "title": "Sample Video",
        "url": "https://example.com/sample-video",
        "uploaderName": "john_doe"
      },
      // Additional videos...
    ]
    ```

### 3. Get Video by ID and View

- **Endpoint**: `GET /{videoId}`
- **Description**: Retrieve a specific video by ID.
- **Parameters**:
  - `{videoId}` (String): ID of the video.
- **Response**:
  - 200 OK: Video found. Returns video information.
  - 404 Not Found: Video not found.

### 4. Add Like to Video

- **Endpoint**: `PUT /{videoId}/addLike/{userName}`
- **Description**: Add a like to a specific video. If the user already disliked the video it removes that dislike
- **Parameters**:
  - `{videoId}` (String): ID of the video.
  - `{userName}` (String): Name of the user adding the like.
- **Response**:
  - 200 OK: Like added successfully.
  - 404 Not Found: Video not found.

### 5. Toggle Dislike for Video

- **Endpoint**: `PUT /{videoId}/toggle-dislike/{userName}`
- **Description**: Toggle the dislike status for a specific video. If its liked by the user then removes the like
- **Parameters**:
  - `{videoId}` (String): ID of the video.
  - `{userName}` (String): Name of the user toggling the dislike.
- **Response**:
  - 200 OK: Dislike status toggled successfully.
  - 404 Not Found: Video not found.

### 6. Get Videos by Uploader Name

- **Endpoint**: `GET /list/{uploaderName}`
- **Description**: Retrieve a list of videos uploaded by a specific user.
- **Parameters**:
  - `{uploaderName}` (String): Name of the uploader.
- **Response**:
  - 200 OK: List of videos successfully retrieved.
  - Example Response:
    ```json
    [
      {
        "videoId": "abc123",
        "title": "Sample Video",
        "url": "https://example.com/sample-video",
        "uploaderName": "john_doe"
      },
      // Additional videos...
    ]
    ```

## Error Handling

- 404 Not Found: Video not found.
- 500 Internal Server Error: Unexpected server error.

## Cross-Origin Resource Sharing (CORS)

- Cross-Origin Resource Sharing (CORS) is enabled for the following origin:
- @CrossOrigin(origins = "http://localhost:5173/")



## Authentication[Not Handled]

- No authentication is required for the provided endpoints.

Feel free to customize this template based on your specific requirements and include additional details if needed.







