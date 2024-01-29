package com.gradation.lift.network.interceptor

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import okhttp3.Interceptor
import okhttp3.Response
import java.net.ConnectException
import javax.inject.Inject

/**
 * [ConnectivityInterceptor]
 * 네트워크 연결 상태 확인 interceptor
 * @since 2024-01-19 18:05:07
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
            throw ConnectException("네트워크에 연결되어있지 않습니다.\n네트워크 연결 후 시도해주세요.")
        }
        return chain.proceed(chain.request().newBuilder().build())
    }
}




