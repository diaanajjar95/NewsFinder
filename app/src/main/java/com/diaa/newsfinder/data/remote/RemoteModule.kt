package com.diaa.newsfinder.data.remote

import com.diaa.newsfinder.BuildConfig
import com.diaa.newsfinder.data.remote.newsapi.NewsApiEndpoints
import com.diaa.newsfinder.data.remote.newsapi.NewsApiInterceptor
import com.diaa.newsfinder.data.remote.newsdata.NewsDataEndpoints
import com.diaa.newsfinder.data.remote.newsdata.NewsDataInterceptor
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

internal const val KOIN_NAME_NEWS_API_BASE_URL = "KOIN_NAME_NEWS_API_BASE_URL"
internal const val KOIN_NAME_NEWS_DATA_BASE_URL = "KOIN_NAME_NEWS_DATA_BASE_URL"
internal const val KOIN_NAME_NEWS_API_RETROFIT = "KOIN_NAME_NEWS_API_RETROFIT"
internal const val KOIN_NAME_NEWS_DATA_RETROFIT = "KOIN_NAME_NEWS_DATA_RETROFIT"
internal const val KOIN_NAME_NEWS_API_OKHTTP = "KOIN_NAME_NEWS_API_OKHTTP"
internal const val KOIN_NAME_NEWS_DATA_OKHTTP = "KOIN_NAME_NEWS_DATA_OKHTTP"

val remoteModule = module {

    single<String>(named(KOIN_NAME_NEWS_API_BASE_URL)) {
        BuildConfig.NEWS_API_BASE_URL
    }

    single<String>(named(KOIN_NAME_NEWS_DATA_BASE_URL)) {
        BuildConfig.NEWS_DATA_BASE_URL
    }

    single<Moshi> {
        Moshi.Builder()
            .build()
    }

    single<NewsApiInterceptor>/*(named(KOIN_NAME_NEWS_API_INTERCEPTOR))*/ {
        NewsApiInterceptor()
    }

    single<NewsDataInterceptor>/*(named(KOIN_NAME_NEWS_DATA_INTERCEPTOR))*/ {
        NewsDataInterceptor()
    }

    single<OkHttpClient>(named(KOIN_NAME_NEWS_API_OKHTTP)) {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.addInterceptor(get<NewsApiInterceptor>())
        if (BuildConfig.DEBUG) {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logger)
        }
        builder.build()
    }

    single<OkHttpClient>(named(KOIN_NAME_NEWS_DATA_OKHTTP)) {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.addInterceptor(get<NewsDataInterceptor>())
        if (BuildConfig.DEBUG) {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logger)
        }
        builder.build()
    }

    single<Retrofit>(named(KOIN_NAME_NEWS_API_RETROFIT)) {
        Retrofit.Builder()
            .baseUrl(get<String>(named(KOIN_NAME_NEWS_API_BASE_URL)))
            .addConverterFactory(MoshiConverterFactory.create(get<Moshi>()))
            .client(get<OkHttpClient>(named(KOIN_NAME_NEWS_API_OKHTTP)))
            .build()
    }

    single<Retrofit>(named(KOIN_NAME_NEWS_DATA_RETROFIT)) {
        Retrofit.Builder()
            .baseUrl(get<String>(named(KOIN_NAME_NEWS_DATA_BASE_URL)))
            .addConverterFactory(MoshiConverterFactory.create(get<Moshi>()))
            .client(get<OkHttpClient>(named(KOIN_NAME_NEWS_DATA_OKHTTP)))
            .build()
    }

    single<NewsApiEndpoints> {
        get<Retrofit>(named(KOIN_NAME_NEWS_API_RETROFIT)).create(NewsApiEndpoints::class.java)
    }

    single<NewsDataEndpoints> {
        get<Retrofit>(named(KOIN_NAME_NEWS_DATA_RETROFIT)).create(NewsDataEndpoints::class.java)
    }

}