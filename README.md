# MovieApp

## Features

- Display popular movies titles and images in a grid
- App bar to navigate back when is possible
- Bottom navigation bar to navigate between main screens


## Architecture

### Modules
```
MovieApp/
├── app/                # Presentation layer (UI, DI y Navigation)
├── domain/             # Use cases and domain models
├── data/               # Repository implementations and data sources
└── core/               # Common utils (network, errors, etc.)
```

### Clean Architecture + MVVM
Basic skeleton
```
- app/
  - di/
  - navigation/
  - ui/
    - components/
    - navigation/
    - screens/
  - viewmodel
- domain/
  - model/
  - repository/
  - usecase/
- data/
  - repository/
  - remote/
    - api/
    - dto/
- core/
  - utils/
  - network/
```

## External libraries
- Retrofit
- Okhttp3
- Hilt
- Coil
- Navigation Compose
