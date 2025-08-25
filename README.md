# Parking Finder App

An Android app built with **Kotlin** and **Jetpack Compose** to help users find parking lots near them.  
It demonstrates real-time geolocation, offline caching, background updates, and accessibility-friendly UI.

---

## Features Planning
- **Google Play Services (Maps & Location APIs)**  
  Shows your current location on Google Maps and nearby parking lots.
- **Offline Cache with Room**  
  Saves parking data locally so you can still see results without internet.
- **Connectivity Aware**  
  Displays a graceful banner when offline and falls back to cached data.
- **Background Updates with WorkManager**  
  Periodically refreshes data with retries/backoff for reliability on weak networks.
- **Optional Foreground Service**  
  Tracks location continuously when running.
- **Retrofit 3 + OkHttp 5**  
  Consumes REST APIs with retries and logging.
- **Material 3 + Accessibility (a11y)**  
  Uses Compose Material3, content descriptions, and proper UI semantics.
- **UI Testing**  
  Includes Compose UI tests + Espresso smoke test.

---

## Demo
*(Working_on!)*

---

## Tech Stack
- **Language:** Kotlin (JVM 17, Kotlin 2.2.10)  
- **UI:** Jetpack Compose Material3  
- **Maps & Location:** Google Maps Compose, Play Services Location  
- **Database:** Room  
- **Networking:** Retrofit 3, OkHttp 5, Moshi  
- **Background Work:** WorkManager  
- **Architecture:** Repository pattern + Coroutines + Flow  
- **Testing:** Compose UI, Espresso

---

## Getting Started

### Prerequisites
- [Android Studio Koala+](https://developer.android.com/studio)
- Android SDK 35
- A Google Maps API key

### Setup
1. Clone this repo:
   ```bash
   git clone https://github.com/<your-username>/parking-finder-app.git
   cd parking-finder-app
