package com.gradation.lift.network.interceptor

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import com.gradation.lift.network.common.error.ErrorMessage.CAN_NOT_ACCESS_NETWORK_ERROR_MESSAGE
import okhttp3.Interceptor
import okhttp3.Response
import java.net.ConnectException
import javax.inject.Inject

/**
 * [ConnectivityInterceptor]
 * 네트워크 연결 상태 확인 interceptor
 * @since 2024-03-05 21:57:49
 */
class ConnectivityInterceptor @Inject constructor(
  private val connectivityManager: ConnectivityManager
) : Interceptor {
    private val isConnected: Boolean
        get() {
            val network: Network = connectivityManager.activeNetwork ?: return false
            val actNetwork: NetworkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                else -> false
            }
        }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected) {
            throw ConnectException(CAN_NOT_ACCESS_NETWORK_ERROR_MESSAGE)
        }
        return chain.proceed(chain.request().newBuilder().build())
    }
}




