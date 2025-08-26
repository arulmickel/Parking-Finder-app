package com.arul.parkingfinder.data.api
import retrofit2.http.GET

interface ParkingApi {
    @GET("/parking.json")
    suspend fun getParking(): List<ParkingDto>
}
data class ParkingDto(
    val id: String, val name: String,
    val lat: Double, val lng: Double,
    val pricePerHour: Double
)
