package com.arul.parkingfinder.data.db
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parking")
data class ParkingEntity(
    @PrimaryKey val id: String,
    val name: String,
    val lat: Double,
    val lng: Double,
    val pricePerHour: Double,
    val updatedAt: Long
)
