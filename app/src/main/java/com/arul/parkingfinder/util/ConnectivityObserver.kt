package com.arul.parkingfinder.util
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

sealed interface NetworkStatus { object Available: NetworkStatus; object Unavailable: NetworkStatus }

class ConnectivityObserver(context: Context) {
    private val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    fun observe(): Flow<NetworkStatus> = callbackFlow {
        val cb = object: ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) { trySend(NetworkStatus.Available) }
            override fun onLost(network: Network) { trySend(NetworkStatus.Unavailable) }
        }
        cm.registerDefaultNetworkCallback(cb)
        val active = cm.activeNetwork?.let { n ->
            cm.getNetworkCapabilities(n)?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        } ?: false
        trySend(if (active) NetworkStatus.Available else NetworkStatus.Unavailable)
        awaitClose { cm.unregisterNetworkCallback(cb) }
    }
}
