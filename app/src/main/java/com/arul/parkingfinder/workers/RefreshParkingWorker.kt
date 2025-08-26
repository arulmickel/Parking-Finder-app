package com.arul.parkingfinder.workers
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.arul.parkingfinder.data.repository.ParkingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RefreshParkingWorker(appContext: Context, params: WorkerParameters)
    : CoroutineWorker(appContext, params) {
    override suspend fun doWork() = withContext(Dispatchers.IO) {
        return@withContext try {
            ParkingRepository(applicationContext).refreshFromNetwork()
            Result.success()
        } catch (_: Throwable) { Result.retry() }
    }
}
