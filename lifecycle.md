# Login Screen Lifecycle in Compose Multiplatform with Clean Architecture

This document explains the lifecycle and architecture of the login screen implementation in our Ali2BD app, built with Kotlin Multiplatform and following Clean Architecture principles.

## What is Clean Architecture?

Clean Architecture is a software design philosophy that separates concerns into distinct layers:

1. **Presentation Layer**: UI components and ViewModels
2. **Domain Layer**: Business logic and use cases
3. **Data Layer**: Data sources, repositories, and models

This separation makes the code more maintainable, testable, and scalable.

## Login Screen Components

Our login screen implementation consists of the following components:

### Data Layer

- **Models (LoginModels.kt)**:
  - `LoginRequest`: Contains username and password to be sent to the API
  - `LoginResponse`: Contains the response from the API (tokens and user info)
  - `UserInfo`: Contains user details
  - `ErrorResponse`: Used to parse error responses from the API

- **Network (ApiService.kt, AuthApi.kt)**:
  - `ApiService`: Base configuration for API communication
  - `AuthApi`: Implementation of login API endpoint

- **Repository (AuthRepositoryImpl.kt)**:
  - Implements the `AuthRepository` interface
  - Handles communication between data sources and domain layer
  - Wraps API responses in Result class for error handling

### Domain Layer

- **Repository Interface (AuthRepository.kt)**:
  - Defines the contract for authentication operations
  - Abstracts the data layer from domain layer

- **Use Case (LoginUseCase.kt)**:
  - Contains business logic for login
  - Single responsibility: perform login operation
  - Used by ViewModel to execute login process

### Presentation Layer

- **ViewModel (LoginViewModel.kt)**:
  - Manages UI state using StateFlow
  - Handles user interactions (username/password changes, login button click)
  - Communicates with domain layer through use cases
  - Updates UI state based on results

- **UI State (LoginUiState.kt)**:
  - Data class representing the state of the login screen
  - Contains fields for username, password, loading state, error messages, etc.

- **UI (LoginScreen.kt)**:
  - Composable function that builds the UI
  - Observes state from ViewModel
  - Triggers events in ViewModel based on user interactions

## Lifecycle Flow

### 1. Initialization

When the app starts:
1. Koin initializes all dependencies (ApiService, Repository, UseCase, ViewModel)
2. LoginScreen composable is created
3. LoginViewModel is injected into the LoginScreen
4. Initial LoginUiState is created with empty fields

### 2. User Interaction

As the user interacts with the screen:
1. User types username → `onUsernameChange()` is called → UI state is updated
2. User types password → `onPasswordChange()` is called → UI state is updated
3. User clicks Login → `login()` is called in ViewModel

### 3. Login Process

When login is initiated:
1. ViewModel validates inputs (checks if username/password are not blank)
2. If validation fails, error state is updated
3. If validation passes:
   - Loading state is set to true
   - LoginUseCase is called with username and password
   - LoginUseCase calls repository
   - Repository creates LoginRequest and calls API
   - API makes network request to https://edge.ali2bd.com/api/consumer/v1/auth/login

### 4. Handling Response

After API call completes:
1. Success response:
   - Repository wraps response in Result.success
   - UseCase passes Result to ViewModel
   - ViewModel updates UI state with success values (isLoggedIn = true, user data)
   - UI observes state change and triggers onLoginSuccess callback

2. Error response:
   - Repository catches exception and wraps in Result.failure
   - UseCase passes Result to ViewModel
   - ViewModel updates UI state with error message
   - UI observes state change and displays error message

## State Management

The UI state is managed using Kotlin's StateFlow:

```kotlin
private val _uiState = MutableStateFlow(LoginUiState())
val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()
```

State updates are done through the `update` extension function, which provides a convenient way to create a new state based on the previous one:

```kotlin
_uiState.update { it.copy(username = username) }
```

The UI observes this state using `collectAsState()`:

```kotlin
val uiState by viewModel.uiState.collectAsState()
```

## Key Benefits of This Approach

1. **Separation of Concerns**: Each component has a single responsibility
2. **Testability**: Each layer can be tested independently
3. **Maintainability**: Changes in one layer don't affect others
4. **Reusability**: Components can be reused across different features
5. **Platform Independence**: Core logic works on all platforms (Android, iOS)

## Dependency Injection with Koin

Koin is used for dependency injection, making it easy to provide instances of repositories, use cases, and ViewModels:

```kotlin
// From AppModule.kt
fun appModule(): Module = module {
    // Network
    single { createHttpClient() }
    single { AuthApi(get()) }
    
    // Repositories
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    
    // Use Cases
    factory { LoginUseCase(get()) }
    
    // ViewModels
    factory { LoginViewModel(get()) }
}
```

In the UI, dependencies are injected using `koinInject()`:

```kotlin
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = koinInject()
) {
    // UI implementation
}
```

## Conclusion

This login screen implementation follows Clean Architecture principles and modern Kotlin practices like Coroutines and Flow. The separation into data, domain, and presentation layers makes the code maintainable and testable, while Compose Multiplatform ensures a consistent UI across platforms.
