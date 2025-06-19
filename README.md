# MovieApp

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

## Features

### Navigation

- App bar to navigate back when is possible (in a navigated screen, not the main screen on the
  bottom bar)
- Bottom navigation bar to navigate between main screens

### Home

- Display popular movies titles and images in a grid
- Navigate to movie details when clicked

### Movie Detail
-