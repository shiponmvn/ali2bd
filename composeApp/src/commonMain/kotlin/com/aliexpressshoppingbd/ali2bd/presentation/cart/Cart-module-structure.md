# Cart Module Structure

The Cart feature follows a clean architecture pattern with the following structure:

```
cart/
├── data/                                   # Data Layer
│   ├── api_service/                        # API Communication Services
│   │   ├── CartApiService.kt               # API Service Interface
│   │   └── CartApiServiceImpl.kt           # API Service Implementation
│   ├── req/                                # Request Models (API request DTOs)
│   │   └── CartRequestModels.kt            # Request model definitions
│   └── res/                                # Response Models
│       └── CartModels.kt                   # DTO Models for API responses
│
├── domain/                                 # Domain Layer (Business Logic)
│   ├── model/                              # Domain Models
│   │   └── CartModel.kt                    # Core cart business models
│   └── usecase/                            # Use Cases (Business Operations)
│       └── cart/
│           └── CartUseCase.kt              # Cart operations business logic
│
├── navigation/                             # Navigation Components
│   └── CartNavigation.kt                   # Navigation routing for cart
│
├── presentation/                           # Presentation Layer
│   ├── components/                         # Reusable UI Components
│   │   └── CartComponents.kt               # Cart-specific UI components
│   └── viewmodel/                          # ViewModels
│       └── CartViewModel.kt                # State management for cart
│
└── screen/                                 # Screen Definitions
    ├── CartScreen.kt                       # Main cart screen UI
    └── CartNav.kt                          # Navigation handling for cart
```

## Current Status Notes

- This module follows the same architectural pattern as the ProductList module.
- The API endpoint for this module is: https://edge.ali2bd.com/api/consumer/v1/cart/items
```
