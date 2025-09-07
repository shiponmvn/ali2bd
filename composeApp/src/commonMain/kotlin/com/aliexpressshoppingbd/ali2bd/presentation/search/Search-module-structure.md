

## Search Feature Structure

Detailed structure of the Search feature:

```
search/
├── data/                        # Data layer components
│   ├── api_service/             # API communication
│   │   ├── SearchApiService.kt
│   │   └── SearchApiServiceImpl.kt
│   ├── req/                     # Request models
│   └── res/                     # Response models
│       └── SystemConfigModels.kt
├── domain/                      # Domain layer
│   ├── model/                   # Domain models
│   │   └── SearchCustomModel.kt
│   ├── repository/              # Repository interfaces
│   └── usecase/                 # Use cases
│       └── system_config/
│           └── SystemConfigUseCase.kt
├── presentation/                # UI components
│   ├── components/              # Reusable UI components
│   │   └── SearchComponents.kt
│   └── viewmodel/               # ViewModels
│       └── SearchViewModel.kt
└── screen/                      # Screen composables
    └── SearchScreen.kt          # Main search screen UI
```
