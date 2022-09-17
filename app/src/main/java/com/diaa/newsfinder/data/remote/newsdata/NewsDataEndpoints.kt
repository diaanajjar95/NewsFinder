package com.diaa.newsfinder.data.remote.newsdata

import com.diaa.newsfinder.data.remote.newsdata.models.NewsDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsDataEndpoints {

    @GET("api/{page}/news")
    suspend fun getNewsList(
        @Path("page") page: Int,
        @Query("q") query: String
    ): Response<NewsDataResponse>

}