package com.arul.parkingfinder
import android.app.Application
import androidx.work.*

import com.arul.parkingfinder.workers.RefreshParkingWorker
import java.util.concurrent.TimeUnit

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val work = PeriodicWorkRequestBuilder<RefreshParkingWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "refresh_parking", ExistingPeriodicWorkPolicy.UPDATE, work
        )
    }
}
