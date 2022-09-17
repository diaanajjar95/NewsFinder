package com.diaa.newsfinder.data.news.api

import com.diaa.newsfinder.data.news.api.models.NewsApiResponse
import com.diaa.newsfinder.data.remote.ApiDefaultResponse

interface NewsApiDataSource {

    suspend fun getNewsApiList(
        q: String,
        from: String,
        sortBy: String
    ): ApiDefaultResponse<NewsApiResponse>

    suspend fun searchBy(
        country: String,
        category: String
    ): ApiDefaultResponse<NewsApiResponse>

}