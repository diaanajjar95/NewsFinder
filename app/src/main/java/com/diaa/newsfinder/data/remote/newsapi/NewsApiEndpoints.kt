package com.diaa.newsfinder.data.remote.newsapi

import com.diaa.newsfinder.data.remote.newsapi.models.NewsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiEndpoints {

    @GET("v2/everything/")
    suspend fun getNewsList(
        @Query("q") query: String,
        @Query("from") from: String,
        @Query("sortBy") sortBy: String
    ): Response<NewsApiResponse>

    @GET("v2/top-headlines/")
    suspend fun searchBy(
        @Query("country") country: String,
        @Query("category") category: String
    ): Response<NewsApiResponse>


}