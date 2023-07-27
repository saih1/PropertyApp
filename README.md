# 🏠 PropertyApp
PropertyApp is a sample Android application developed using Jetpack Compose and follows the Modern App Architecture, incorporating modern design principles and development practices to create a robust and maintainable codebase.

## 📱 Screenshots
| Light | Dark | Loading and Error |
|:---:|:---:|:---:|
| <img src="https://github.com/saih1/PropertyApp/assets/72930376/18badbba-4914-43ae-818e-4027e35487fb" alt="list_light" width="50%"> | <img src="https://github.com/saih1/PropertyApp/assets/72930376/77d95fb3-1b33-46a1-8585-0103cf5a141c" alt="list_dark" width="50%"> | <img src="https://github.com/saih1/PropertyApp/assets/72930376/119c1bb7-879b-4f3e-b473-db0f8fbb8b50" alt="loading" width="50%"> |
| <img src="https://github.com/saih1/PropertyApp/assets/72930376/bc6668cb-3d58-4dd7-94fe-82a34e9681d5" alt="detail_light" width="50%"> | <img src="https://github.com/saih1/PropertyApp/assets/72930376/5a983b77-c7cd-4a1a-b687-6ee928a132f6" alt="detail_dark" width="50%"> | <img src="https://github.com/saih1/PropertyApp/assets/72930376/5a948f90-f742-4ae0-91e6-5c331d5b6a7c" alt="error" width="50%"> |

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

---
## 🧪 Local Unit Test
<img width="60%" alt="unitTestSS" src="https://github.com/saih1/PropertyApp/assets/72930376/e40d3e62-d0c6-4c68-9514-3b6c04fea063">

