package com.example.core.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url

        val urlWithApiKey = originalUrl.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()

        val requestBuilder = original.newBuilder().url(urlWithApiKey)
        val request = requestBuilder.build()

        return chain.proceed(request)
    }
}
