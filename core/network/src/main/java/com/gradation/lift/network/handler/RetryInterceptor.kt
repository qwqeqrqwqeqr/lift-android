package com.gradation.lift.network.handler

import android.os.SystemClock.sleep
import android.util.Log
import com.gradation.lift.network.common.Constants.NETWORK_RETRY_DELAY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RetryInterceptor @Inject constructor(
) : Interceptor {
     override fun intercept(chain: Interceptor.Chain): Response {
        return with(chain) {
            val newRequest = request().newBuilder().build()
            var response = proceed(newRequest)
            var tryCount = 0
            while (!response.isSuccessful && tryCount < 3){
                Log.d("test","통신을 실패하였습니다 재시도 합니다... (${tryCount+1}/3)")
                sleep(NETWORK_RETRY_DELAY)
                tryCount++
                response.close()
                response = chain.proceed(request());
            }
            response
        }
    }

}