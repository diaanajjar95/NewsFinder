package com.diaa.newsfinder.data.news.api

import com.diaa.newsfinder.data.remote.ApiDefaultResponse
import com.diaa.newsfinder.data.remote.newsapi.NewsApiGenericResponse
import com.diaa.newsfinder.data.remote.newsapi.models.NewsApiResponse

interface NewsApiDataSource {

    suspend fun getNewsApiList(
        q: String,
        from: String,
        sortBy: String
//    ): ApiDefaultResponse<NewsApiGenericResponse<NewsApiResponse>>
    ): ApiDefaultResponse<NewsApiResponse>

    suspend fun searchBy(
        country: String,
        category: String
//    ): ApiDefaultResponse<NewsApiGenericResponse<NewsApiResponse>>
    ): ApiDefaultResponse<NewsApiResponse>

}