package com.diaa.newsfinder.data.remote.newsapi

import okhttp3.Interceptor
import okhttp3.Response

class NewsApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val httpUrl = original.url
        val httpNewUrl = httpUrl.newBuilder()
            .addQueryParameter("apiKey", com.diaa.newsfinder.BuildConfig.NEWS_API_API_KEY).build()
        val newRequestBuilder = original.newBuilder().url(httpNewUrl)
        val request = newRequestBuilder.build()
        return chain.proceed(request)
    }
}