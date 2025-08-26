package com.arul.parkingfinder.data.repository
import android.content.Context
import com.arul.parkingfinder.data.api.NetworkModule
import com.arul.parkingfinder.data.db.AppDatabase
import com.arul.parkingfinder.data.db.ParkingEntity
import kotlinx.coroutines.flow.Flow

class ParkingRepository(context: Context) {
    private val dao = AppDatabase.get(context).parkingDao()
    fun observe(): Flow<List<ParkingEntity>> = dao.observeAll()

    suspend fun refreshFromNetwork() {
        val now = System.currentTimeMillis()
        val list = NetworkModule.api.getParking().map {
            ParkingEntity(it.id, it.name, it.lat, it.lng, it.pricePerHour, now)
        }
        dao.upsertAll(list)
    }

    suspend fun seedDemoIfEmpty() {
        val now = System.currentTimeMillis()
        val demo = listOf(
            ParkingEntity("1","Wabash Garage",41.8827,-87.6260,8.0, now),
            ParkingEntity("2","West Loop Lot",41.8820,-87.6517,5.0, now),
        )
        dao.upsertAll(demo)
    }
}
// inside MapScreen(...)
LaunchedEffect(Unit) {
    // Try network; if it fails (airplane mode), keep cached data
    runCatching { repo.refreshFromNetwork() }
        .onFailure { repo.seedDemoIfEmpty() }
}
