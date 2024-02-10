package com.suslanium.gafarov.data.interceptor

import com.suslanium.gafarov.data.Constants
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        if (request.header(Constants.AUTH_HEADER) == null) {
            builder.addHeader(
                Constants.AUTH_HEADER, Constants.API_KEY
            )
        }

        return chain.proceed(builder.build())
    }
}