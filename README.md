# MovieApp

## Features
- Display popular movies titles


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
