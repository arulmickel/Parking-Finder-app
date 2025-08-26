package com.arul.parkingfinder.data.db
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ParkingDao {
    @Query("SELECT * FROM parking ORDER BY pricePerHour ASC")
    fun observeAll(): Flow<List<ParkingEntity>>

    @Upsert
    suspend fun upsertAll(items: List<ParkingEntity>)
}
