# 🏠 PropertyApp
PropertyApp is a sample Android application developed using Jetpack Compose and follows the Modern App Architecture, incorporating modern design principles and development practices to create a robust and maintainable codebase.

## 📱 Screenshots
<img src="https://github.com/saih1/PropertyApp/assets/72930376/680b6908-1715-4613-a9bc-f58815f2c6d8" alt="phone_listScreen_light" width="15%">
<img src="https://github.com/saih1/PropertyApp/assets/72930376/b83abe3c-f1c7-485d-bb52-f1c1b2c9cfb4" alt="phone_listScreen_dark" width="15%">
<img src="https://github.com/saih1/PropertyApp/assets/72930376/9054e73f-7a16-463e-9e97-e00912c6fb36" alt="phone_detailScreen_light" width="15%">
<img src="https://github.com/saih1/PropertyApp/assets/72930376/7d1a7e01-8715-4019-b4a5-cd0c52696813" alt="phone_detailScreen_dark" width="15%">

## 🛠️ Architecture
The app follows a well-defined and organised architecture to ensure scalability and maintainability. Key architectural principles include:

* Reactive and Layered
* Unidirectional Data Flow
* Flows
* Dependency Injection

---
### Data layer
The data layer plays a crucial role in exposing data to the rest of the app. It consists of two main components:

1. Property API (Remote Datasource): This component handles interactions with the remote data source and fetches property data.

2. Property Repository: Responsible for abstracting the data source implementation details from the domain layer, the Property Repository provides a clean interface to access property data within the app.

---
### Domain layer

The domain layer encapsulates the business logic of the application, enhancing reusability and testability. It includes the getPropertiesUseCase, which is responsible for converting the data received from the API into a format that is more view-friendly. Additionally, it efficiently handles and emits success and error cases through appropriate flow data, ensuring a smooth data flow throughout the app.

---
### UI layer
The UI layer serves as the front-end of the application, consuming data from the domain layer. It consists of two main components:
1. ViewModel: plays a central role in the UI layer, publishing data to be observed and consumed by the UI elements, following the UDF (unidirectional data flow) pattern.
2. UI Elements: responsible for presenting the data to the users, and sending events/ user actions to the ViewModel.

The UI layer establishes a clear separation of concerns, with the ViewModel solely responsible for publishing data, while the UI elements interact with the ViewModel by sending events, forming a unidirectional data flow.

---
## 📚 Libraries
**Dagger-Hilt**
- Although Koin DI is more appropriate for an app of this size, Hilt is a subjective preference. 

**Retrofit**
- It makes networking so much easier. 

**Moshi**
- I think Moshi is a better JSON processor than Gson. Also, I’m more familiar with it. 

**Coil**
- Image loading library that is light, fast, modern and easy to use (according to https://github.com/coil-kt/coil)

**Accompanist Navigation with Compose**
- Deprecated because all the functionalities will be in `androidx.navigation.compose` _(Still in Alpha release as of now)_.

**Google Truth**
- Personal preference purely due to readability and expressiveness.
