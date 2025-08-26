package com.arul.parkingfinder.util
import okhttp3.Interceptor
import okhttp3.Response

class RetryInterceptor(private val maxRetries: Int = 3) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var tries = 0; var last: Exception? = null
        while (tries < maxRetries) {
            try { return chain.proceed(chain.request()) }
            catch (e: Exception) { last = e; tries++; if (tries < maxRetries) Thread.sleep((250L * (1 shl (tries - 1))).coerceAtMost(2000)) }
        }
        throw last ?: IllegalStateException("Network error")
    }
}
