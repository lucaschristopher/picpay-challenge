package com.picpay.desafio.core.data.network.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

fun interface NetworkHelper {
    suspend fun isConnected(): Boolean
}

class NetworkHelperImpl(
    private val context: Context,
    private val dispatcher: CoroutineDispatcher,
) : NetworkHelper {

    override suspend fun isConnected(): Boolean = withContext(dispatcher) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return@withContext false
        val capabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return@withContext false
        return@withContext capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}
