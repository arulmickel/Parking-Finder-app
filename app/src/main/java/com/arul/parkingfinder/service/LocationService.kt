<uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
<uses-permission android:name="android.permission.FOREGROUND_SERVICE_LOCATION" />
<service
android:name=".service.LocationService"
android:exported="false"
android:foregroundServiceType="location" />
