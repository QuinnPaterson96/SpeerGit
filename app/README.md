# SpeerGit

A GitHub user explorer Android app built as part of a technical assessment. The app allows users to search GitHub usernames, view their profiles, and explore followers/following — all with a clean UI and responsive experience.

---

## ✨ Features

- 🔍 **Search GitHub users** by username
- 👤 **Profile view** with avatar, name, bio, followers, and following counts
- 👥 **Followers / Following list views** with deep linking
- 🔄 **Pull-to-refresh** support on all user-related views
- 💾 **Profile caching** with Room database and cache invalidation
- 🚦 Graceful fallback to cached data when offline
- 🧭 Jetpack Navigation Compose for intuitive screen transitions

---

## 🧱 Tech Stack

- **Kotlin**
- **Jetpack Compose** for UI
- **MVVM Architecture**
- **Hilt** for dependency injection
- **Retrofit + Moshi** for GitHub API
- **Room** for local caching
- **AndroidX `pullRefresh()`** for modern refresh UX
- **Coil** for image loading

---

## 🚀 Setup Instructions

### 1. Clone the repository


git clone https://github.com/QuinnPaterson96/SpeerGit.git
cd SpeerGit

### 2. Open in Android Studio

- Requires **Android Studio Hedgehog** or newer
- Compile SDK: **34**
- Minimum SDK: **24**

### 3. Run the app

- Run on an emulator or physical device
- App launches directly into the user search screen

---

## 🧪 Stretch Goals Implemented

- ✅ **Pull-to-Refresh** using `Modifier.pullRefresh()` from AndroidX
- ✅ **Profile Caching** with Room
  - Profiles are cached on successful API calls
  - Cache is invalidated on pull-to-refresh or after 1 hour
- ✅ **Offline Fallback**
  - If a refresh fails (e.g. no network), cached profiles are still shown

---

## 🙌 Notes

- No GitHub API key is required – all data uses public endpoints
- Built entirely with **Jetpack Compose**
- Some features (like pull-to-refresh) use `@OptIn(ExperimentalMaterialApi::class)` per AndroidX best practices
- Designed to demonstrate practical understanding of modern Android development patterns

---

## ⏱ Time Tracking

| Task                                                   | Time Spent |
|--------------------------------------------------------|------------|
| Reviewing API, thinking through architecture           | 30 mins    |
| Initial project setup, configuring libraries in Gradle | 1 hr       |
| Debugging, building profile/followers/following views  | 2 hr       |
| Swipe-to-refresh, data caching, quality polish         | 1 hr 45m   |
| Preparing submission (video, README, testing)          | 25 mins    |
| **Total**                                              | **~5 hr 40m** |

---

## 📬 Contact

Developed by **Quinn Paterson**   
🔗 [LinkedIn](https://www.linkedin.com/in/quinn-paterson-694656123)
