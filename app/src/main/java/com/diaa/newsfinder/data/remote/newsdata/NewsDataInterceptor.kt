package com.diaa.newsfinder.data.remote.newsdata

import com.diaa.newsfinder.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class NewsDataInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val httpUrl = original.url
        val newHttpUrl =
            httpUrl.newBuilder().addQueryParameter("apiKey", BuildConfig.NEWS_DATA_API_KEY).build()
        val newRequestBuilder = original.newBuilder().url(newHttpUrl)
        val newRequest = newRequestBuilder.build()
        return chain.proceed(newRequest)
    }
}