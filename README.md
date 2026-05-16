# Kutira Kone 🏡

Kutira Kone is an Android application developed using Kotlin and Jetpack Compose to connect buyers and sellers in the livestock marketplace. The app allows users to browse animal listings, chat with sellers, and manage purchase requests through a modern mobile interface.

---

## 📱 Features

- 🔐 User authentication (Login screen)
- 🐄 Browse livestock and farm listings
- 💬 In-app chat system
- 📥 Manage purchase requests
- 🧭 Navigation between screens using Jetpack Compose Navigation
- 🏗️ MVVM architecture with ViewModels
- ☁️ Firebase-ready project structure (currently uses mock services)

---

## 🛠️ Tech Stack

- **Language:** Kotlin
- **UI Toolkit:** Jetpack Compose
- **Architecture:** MVVM (Model-View-ViewModel)
- **Navigation:** Navigation Compose
- **Asynchronous Programming:** Kotlin Coroutines
- **UI Components:** Material 3
- **IDE:** Android Studio

---

## 📂 Project Structure

```text
KutiraKone/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/kutirakone/
│   │   │   ├── ui/screens/
│   │   │   │   ├── LoginScreen.kt
│   │   │   │   ├── ListingsScreen.kt
│   │   │   │   ├── ChatScreen.kt
│   │   │   │   └── RequestsScreen.kt
│   │   │   ├── viewmodel/
│   │   │   │   ├── AuthViewModel.kt
│   │   │   │   └── ListingViewModel.kt
│   │   |   ├── service/
│   │   │   |   ├── FirebaseService.kt
│   │   │       ├── AuthService.kt
│   │   │   └── MainActivity.kt
│   │   ├── ui/
│   │   │   ├── screens/
│   │   │   │   ├── LoginScreen.kt
│   │   │   │   ├── RegisterScreen.kt
│   │   │   │   ├── ListingsScreen.kt
│   │   │   │   ├── ListingDetailScreen.kt
│   │   │   │   ├── ChatScreen.kt
│   │   │   │   ├── RequestsScreen.kt
│   │   │   │   ├── FavoritesScreen.kt
│   │   │   │   └── ProfileScreen.kt
│   └── build.gradle
├── README.md
└── .gitignore
