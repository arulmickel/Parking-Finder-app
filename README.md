# Parking Finder (Android, Kotlin, Compose)

Find nearby parking with **Google Maps & Play Services Location**, built for **offline-first** reliability and **background refresh** on spotty networks. Designed with **MVVM + Coroutines/Flow**, **Room** cache, and **WorkManager** for scheduled updates (retries/backoff). Ready for **geofencing (micro-fencing)** and **FCM alerts**.


---

## Features Planning
- 🗺️ Maps & Location: Google Maps + Fused Location Provider (current spot, nearby lots)
- 📦 Offline-first: Room cache; graceful offline banner; auto-resync when back online
- 🔄 Background refresh: WorkManager with constraints, retries, and exponential backoff
- 🧭 (Planning..) Micro-fencing: GeofencingClient enter/exit triggers for lot proximity
- 🔔 (Working..) Notifications: FCM push for availability/price changes
- ♿ Accessibility: Material 3 semantics, content descriptions, dark mode ready


---

## Demo
*(Working_on!)*

---

## Tech Stack
- Language: Kotlin (JDK 17) • Coroutines • Flow
- UI: Jetpack Compose Material 3
- Location & Maps: Google Maps Compose, Play Services Location (Fused)
- Data: Room (cache), DataStore/Prefs (settings)
- Networking: Retrofit 3, OkHttp 5, Moshi
- Background work: WorkManager (constraints, backoff)
- Architecture: MVVM + Repository pattern
- Testing: Compose UI tests, Espresso smoke tests


---

## Getting Started

### Prerequisites
- [Android Studio Koala+](https://developer.android.com/studio)
- Android SDK 35
- A Google Maps API key

  ## Next Steps (Working_on)
- Add GeofencingClient zones for “arriving at lot” nudges(looking for free source)
- Wire FCM topic per city/lot for availability alerts
- Add Crashlytics + Analytics event taxonomy
- Power optimizations: batching + foreground service only when navigating


### Setup
1. Clone this repo:
   ```bash
   git clone [https://github.com/arulmickel/parking-finder-app.git]
   cd parking-finder-app
