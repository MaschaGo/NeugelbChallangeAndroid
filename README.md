
# **MovieApp**

MovieApp is an Android application built using Kotlin and Jetpack Compose. The app allows users to search for movies, browse the latest movie lists, and view detailed information about each movie. The app uses TMDB (The Movie Database) API for fetching movie data and supports features like infinite scrolling, search with auto-complete, and error handling with pagination.

## **Features**

- **Latest Movies List**: Browse the latest movies with infinite scrolling.
- **Movie Details**: View detailed information about each movie, including description, release date, genres, and more.
- **Search Movies**: Search for movies with auto-complete functionality.
- **Error Handling**: Error handling for network issues.
- **Paging Support**: Efficient loading of movie data using Android’s Paging3 library.
- **Image Loading**: Movie posters and backdrops are loaded using Coil.
- **Theme Support**: Support for dark and light themes using Jetpack Compose's Material3 theme.


## **Project Architecture**

The project follows **Clean Architecture** principles with a modular structure that separates concerns into different layers:

1. **App Module (Android Module)**: Contains the main entry point of the app. 

2. **Presentation Module**: Contains UI components, navigation logic, ViewModels, and UI state management:
   - **Screens**: Movie list, movie details, and search screens are defined here using Jetpack Compose.
   - **Navigation**: Uses Jetpack Compose’s `NavHost` for managing navigation between screens.
   - **ViewModel**: Manages UI state and interacts with the domain layer.

3. **Domain Module**: Contains the core business logic and use cases. This module is independent of any UI or data frameworks and defines the application's core operations, such as fetching movie details or searching for movies.

4. **Data Module**: Responsible for data access and API integration. This module includes repositories, DTOs, and network services for interacting with TMDB API.

5. **Core Module**: Contains shared resources like theme definitions (colors, typography) and utilities. This module is used across the app for styling and other common operations.


## **Dependency Injection with Koin**

This project uses **Koin** as the **Dependency Injection (DI) Framework**. 


## **Dependency Management with Dependency Catalog**

To efficiently manage dependencies across modules, this project uses **Gradle’s Versions Catalog**. The catalog allows defining versions and dependencies in one place, ensuring consistency across modules.


## **Screens**

1. **Movies Screen**:
   - Displays the latest movies fetched from the TMDB API.
   - Implements infinite scrolling using Paging3.
   - Allows navigation to the **Movie Details Screen**.

2. **Movie Details Screen**:
   - Shows detailed information about a specific movie, including the poster, backdrop, description, release date, genres, and more.

3. **Search Screen**:
   - Implements a search bar with auto-complete functionality.
   - Displays search results in a paginated list format.


## **Libraries and Tools**

### **Core Libraries**:
- **Kotlin**: Language used for development.
- **Jetpack Compose**: Modern toolkit for building native UI in Android.
- **Retrofit**: Used for network calls to TMDB API.
- **Coil**: Image loading library for loading movie posters and backdrops.
- **Paging 3**: Efficient pagination for loading movie data.
- **Koin**: Dependency Injection framework.

### **Testing Libraries**:
- **JUnit**: Unit testing framework.
- **MockWebServer**: Simulate API responses for unit testing.
- **Mockito/MockK**: Mocking library for testing.

---
 **Add API Key**:
   - Obtain your API key from [TMDB](https://www.themoviedb.org/).
   - Create a `apikey.properties` file in the project root and add the following line:
 	` 	tmdb_api_key=your_api_key_here `

