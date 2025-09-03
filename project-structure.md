# Ali2BD Project Structure Guide

## Overview
This is a **Compose Multiplatform** project implementing **Clean Architecture** for the Ali2BD shopping application. The project supports both Android and iOS platforms with shared business logic and UI components.

## 🏗️ Architecture Pattern
**Clean Architecture** with three main layers:
- **Presentation Layer** - UI components and ViewModels
- **Domain Layer** - Business logic and use cases
- **Data Layer** - API services and repository implementations

## 📁 Root Directory Structure

```
Ali2BD/
├── build.gradle.kts              # Root build configuration
├── settings.gradle.kts           # Project modules configuration
├── gradle.properties             # Gradle properties
├── local.properties              # Local SDK paths
├── lifecycle.md                  # Login screen lifecycle guide
├── project-structure.md          # This file
├── README.md                     # Project documentation
├── gradlew                       # Gradle wrapper (Unix)
├── gradlew.bat                   # Gradle wrapper (Windows)
├── gradle/                       # Gradle wrapper files
│   ├── libs.versions.toml        # Version catalog
│   └── wrapper/
├── composeApp/                   # Main multiplatform module
├── iosApp/                       # iOS-specific configuration
└── build/                        # Build outputs
```

## 📱 ComposeApp Module Structure

### Main Source Sets
```
composeApp/
├── src/
│   ├── commonMain/               # Shared code for all platforms
│   │   └── kotlin/
│   │       └── com/aliexpressshoppingbd/ali2bd/
│   │           ├── model/        # Data models and DTOs
│   │           ├── di/           # Dependency injection modules
│   │           ├── data/         # Data layer implementation
│   │           ├── domain/       # Business logic layer
│   │           ├── presentation/ # UI layer (Compose screens & ViewModels)
│   │           └── utils/        # Utility classes and extensions
│   ├── androidMain/              # Android-specific implementations
│   ├── iosMain/                  # iOS-specific implementations
│   └── commonTest/               # Shared unit tests
```

## 🎯 Clean Architecture Layer Structure

### 1. Model Layer (`model/`)
```
model/
├── LoginModels.kt                # Login-related data classes
├── ProductModels.kt              # Product-related data classes
├── OrderModels.kt                # Order-related data classes
└── CommonModels.kt               # Shared models across features
```

**Purpose**: Data Transfer Objects (DTOs) and model classes for API communication and data representation.

### 2. Data Layer (`data/`)
```
data/
├── repository/                   # Repository implementations
│   ├── LoginRepositoryImpl.kt
│   ├── ProductRepositoryImpl.kt
│   └── OrderRepositoryImpl.kt
├── remote/                       # Network layer
│   ├── api/                      # API service interfaces
│   │   ├── LoginApiService.kt
│   │   ├── ProductApiService.kt
│   │   └── OrderApiService.kt
│   ├── client/                   # HTTP client configuration
│   │   └── NetworkClient.kt
│   └── dto/                      # Network-specific DTOs
├── local/                        # Local storage (if needed)
│   ├── database/
│   └── preferences/
└── mapper/                       # Data mapping utilities
    ├── LoginMapper.kt
    └── ProductMapper.kt
```

**Purpose**: Handles data sources (API, database, cache) and implements repository interfaces from domain layer.

### 3. Domain Layer (`domain/`)
```
domain/
├── repository/                   # Abstract repository interfaces
│   ├── UserRepository.kt
│   ├── ProductRepository.kt
│   └── OrderRepository.kt
├── usecase/                      # Business logic use cases
│   ├── auth/
│   │   ├── LoginUseCase.kt
│   │   ├── LogoutUseCase.kt
│   │   └── ValidateCredentialsUseCase.kt
│   ├── product/
│   │   ├── GetProductsUseCase.kt
│   │   └── SearchProductsUseCase.kt
│   └── order/
│       ├── CreateOrderUseCase.kt
│       └── GetOrderHistoryUseCase.kt
├── model/                        # Domain models (business entities)
│   ├── User.kt
│   ├── Product.kt
│   └── Order.kt
└── util/                         # Domain-specific utilities
    ├── Result.kt                 # Result wrapper class
    └── ValidationRules.kt
```

**Purpose**: Contains business logic, use cases, and domain models. Independent of external frameworks.

### 4. Presentation Layer (`presentation/`)
```
presentation/
├── ui/                          # Composable screens
│   ├── auth/
│   │   ├── LoginScreen.kt       # Login UI composable
│   │   ├── RegisterScreen.kt
│   │   └── components/          # Login-specific UI components
│   │       └── LoginForm.kt
│   ├── home/
│   │   ├── HomeScreen.kt
│   │   └── components/
│   ├── product/
│   │   ├── ProductListScreen.kt
│   │   ├── ProductDetailScreen.kt
│   │   └── components/
│   └── common/                  # Shared UI components
│       ├── LoadingIndicator.kt
│       ├── ErrorDialog.kt
│       └── CustomTextField.kt
├── viewmodel/                   # ViewModels for each screen
│   ├── LoginViewModel.kt
│   ├── HomeViewModel.kt
│   └── ProductViewModel.kt
├── state/                       # UI state classes
│   ├── LoginState.kt
│   ├── HomeState.kt
│   └── ProductState.kt
├── navigation/                  # Navigation setup
│   ├── AppNavigation.kt
│   └── NavDestinations.kt
└── theme/                       # UI theming
    ├── Color.kt
    ├── Typography.kt
    └── Theme.kt
```

**Purpose**: UI components, ViewModels, and presentation logic. Handles user interactions and displays data.

### 5. Dependency Injection (`di/`)
```
di/
├── AppModule.kt                 # Application-level dependencies
├── NetworkModule.kt             # Network-related dependencies
├── RepositoryModule.kt          # Repository implementations
├── UseCaseModule.kt            # Use case dependencies
└── ViewModelModule.kt          # ViewModel dependencies
```

**Purpose**: Koin modules for dependency injection setup.

### 6. Utils (`utils/`)
```
utils/
├── extensions/                  # Extension functions
│   ├── StringExtensions.kt
│   └── ComposeExtensions.kt
├── constants/                   # App constants
│   ├── ApiConstants.kt
│   └── AppConstants.kt
└── validators/                  # Input validation utilities
    ├── EmailValidator.kt
    └── PasswordValidator.kt
```

## 🔧 Build Configuration Files

### Root Level
- **`build.gradle.kts`** - Root project build script
- **`settings.gradle.kts`** - Module configuration
- **`gradle.properties`** - Global gradle properties
- **`local.properties`** - Local SDK paths (not in version control)

### Module Level
- **`composeApp/build.gradle.kts`** - Main module build configuration
  - Kotlin Multiplatform setup
  - Compose Multiplatform dependencies
  - Platform-specific configurations

## 📚 Key Libraries Used

### Multiplatform
- **Kotlin Multiplatform** - Shared code
- **Compose Multiplatform** - UI framework
- **Kotlinx Serialization** - JSON serialization
- **Ktor Client** - HTTP client
- **Koin** - Dependency injection

### Platform Specific
- **Android**: AndroidX libraries
- **iOS**: Swift interop

## 🌊 Data Flow

```
UI (Compose) → ViewModel → UseCase → Repository → API Service
                   ↓
              UI State ← Domain Model ← DTO Mapping ← Network Response
```

## 🧪 Testing Structure

```
commonTest/
├── domain/
│   └── usecase/                # Use case unit tests
├── data/
│   └── repository/             # Repository tests
└── presentation/
    └── viewmodel/              # ViewModel tests
```

## 🚀 Getting Started

1. **Clone** the repository
2. **Open** in Android Studio or IntelliJ IDEA
3. **Sync** Gradle files
4. **Run** on Android or iOS

## 📝 Development Guidelines

### File Naming Conventions
- **Screens**: `*Screen.kt` (e.g., `LoginScreen.kt`)
- **ViewModels**: `*ViewModel.kt` (e.g., `LoginViewModel.kt`)
- **Use Cases**: `*UseCase.kt` (e.g., `LoginUseCase.kt`)
- **Repositories**: `*Repository.kt` for interfaces, `*RepositoryImpl.kt` for implementations
- **API Services**: `*ApiService.kt` (e.g., `LoginApiService.kt`)

### Package Naming
- Use reverse domain naming: `com.aliexpressshoppingbd.ali2bd.*`
- Feature-based packaging within each layer
- Keep related classes together

### Dependencies Direction
- **Presentation** depends on **Domain**
- **Data** depends on **Domain**  
- **Domain** depends on nothing (pure business logic)

This structure ensures maintainability, testability, and scalability for your Compose Multiplatform application following Clean Architecture principles.
