# FastRent Android App

## General
FastRent is a rental apartments app with mocked functionality designed to demonstrate good and
modern mobile app architecture. The project showcases a well-organized modular architecture that follows clean architecture principles. It contains examples of integration with popular libraries, including Dagger Hilt for dependency injection, Room Database for local data storage, Navigation Component for type-safe navigation, and Android Architecture Components for
reactive programming. It also includes helpful classes and interfaces for handling common app
functionality such as logging, repository, app components initialization.

In addition, the app has been configured with shared third-party dependencies declaration, enabling easier management of dependencies across modules. The project also includes CI integration, lint and static analysis reports to ensure code quality, and obfuscation to protect against reverse
engineering. Build types are configurable, allowing for different versions of the app to be built with different configurations and optimizations.

The project extensively uses Kotlin Coroutines and Flows for reactive programming style, making the code more concise and readable. Additionally, all classes are lifecycle-aware, which means they can automatically handle configuration changes such as screen rotations without losing their state. This is achieved by using ViewModel from the Android Architecture Components library, which also makes it easier to separate business logic from presentation logic.

The code is also well-organized into modules, following the principles of clean architecture, making it easier to understand and maintain. Overall, the project serves as a valuable resource for developers looking to improve their skills in Android app development and best practices.
## Architecture
The app follows the best practices provided by Google and adheres to the Clean Architecture principles based on MVVM. It is organized into four modules: data, domain, presentation, and app.
Overall, the app's modular architecture allows for a clear separation of concerns and improves maintainability and scalability. The separation of modules also allows for better testing, as each module can be tested in isolation. The app's adherence to clean architecture and MVVM principles also ensures a clear separation of business logic and presentation logic, making it easier to maintain and modify the codebase over time.
#### Data module
The data module is a framework-related module that implements APIs and repositories and interacts with the system. It depends on the domain module to provide the necessary interfaces and abstractions implements.
#### Domain module
The domain module is a separated module with business logic and interfaces. It doesn't depend on other modules or framework classes except Hilt, Coroutines and Flows.
#### Presentation module
The presentation module contains UI-related classes, including activities, fragments, views, navigation, and resources such as strings, styles, layouts, and drawables.
#### App module
The app module combines all modules and initializes the app and its components.
The app module configures the application. It is responsible for the app's lifecycle and manages the injection of dependencies.
This module serves as the entry point to the application and ties together all the other modules to create the complete app.
In addition, the app module also configures the application in both code and Gradle script. It defines the app's build types, including debug, release, and custom build types that can be configured for specific use cases. This allows for different configurations and optimizations, such as enabling or disabling certain features or libraries, for different build types.

## Key Libraries and Features
The app uses a variety of libraries and features to enable its functionality and improve user experience. Some of the key libraries and features include:

* **Kotlin Coroutines and Flows:** Coroutines provide a simple and efficient way to manage background tasks and parallelism. Flows are a reactive streams library that enables the app to observe and react to changes in data in a more efficient and performant way. App doesn't use LiveData from Android Jetpack; instead, it uses StateFlow and MutableStateFlow for lifecycle-aware components. [[Link]](https://kotlinlang.org/docs/coroutines-overview.html)
* **Android Architecture Components:** These components provide a set of libraries and best practices for building robust, testable, and maintainable Android apps. The app makes use of several of these components, including ViewModel and Room. [[Link]](https://kotlinlang.org/docs/coroutines-overview.html)
* **Dagger Hilt:** This library provides a way to manage dependencies and perform dependency injection in Android apps. The app uses Hilt to enable modularity and to make testing and maintenance easier. [[Link]](https://dagger.dev/hilt/)
* **ViewBinding:** This feature enables type-safe access to view elements in layouts, making it easier and more efficient to manage UI elements in the app. [[Link]](https://developer.android.com/topic/libraries/view-binding)
* **WorkManager:** This library provides a way to perform background tasks in a way that is optimized for battery life and device performance. The app uses WorkManager to manage and schedule background tasks, such as database syncing and data processing. [[Link]](https://developer.android.com/topic/libraries/architecture/workmanager)
* **SLF4J:** This library provides a simple and efficient logging interface for the app, making it easier to debug and diagnose issues. [[Link]](http://www.slf4j.org/)
* **App Startup:** This library provides a way to initialize components when the app starts up. The app uses App Startup to ensure that certain components are initialized before the app can be used. [[Link]](https://developer.android.com/topic/libraries/app-startup)

### Configuration
The app uses the Gradle build system, specifically the Kotlin DSL for build scripts (KTS). This allows for more concise and expressive build scripts. One notable feature of the build system is the use of shared dependency declarations, which centralizes the dependency management and reduces duplication across modules.

In addition to building the app, the Gradle build system is also responsible for tasks such as APK file preparation, code coverage analysis, and code quality report generation. These tasks are essential for maintaining code quality and ensuring that the app runs smoothly.

To ensure a well-organized and maintainable configuration, each build type can be configured with different parameters, such as keys, certificates, and other parameters. This allows for flexibility and customization of the build process.

### App Parameters:
Feature that allows applications to be configured on startup. When an application is launched, it reads a configuration file from the external storage of the app and initializes all components with these parameters like timeouts, logger, keys, and certificates. This feature enables users to change the behavior of the app without having to modify the code. Furthermore, the "App parameters" feature can be disabled for certain build types, such as release, to prevent unauthorized changes to the application's behavior.

## Open Source License
FastRent Android App is open source software released under the [MIT License](https://opensource.org/licenses/MIT). This means that you are free to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the software. However, this software is provided "as is," without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, fitness for a particular purpose, and noninfringement. In no event shall the authors or copyright holders be liable for any claim, damages, or other liability, whether in an action of contract, tort, or otherwise, arising from, out of, or in connection with the software or the use or other dealings in the software.

Please read the full text of the license for more information on your rights and obligations when using this software.

## Conclusion
FastRent Android App is a well-organized and well-architected Android app that demonstrates best practices in mobile app development. The app uses a modular architecture that adheres to clean architecture and MVVM principles, making it easy to understand and maintain. It also includes many popular libraries and features, such as Kotlin Coroutines, Dagger Hilt, and Android Architecture Components, which enable efficient and performant development. Additionally, the app includes helpful classes and interfaces for handling common app functionality, such as logging, repository, and app components initialization, and is configured with shared third-party dependencies, CI integration, lint and static analysis reports, and obfuscation. The app is released under the MIT License and can be used, modified, and distributed freely.