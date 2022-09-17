package com.diaa.newsfinder.data.news.api

import com.diaa.newsfinder.data.remote.ApiDefaultResponse
import com.diaa.newsfinder.data.remote.newsapi.NewsApiEndpoints
import com.diaa.newsfinder.data.remote.newsapi.NewsApiGenericResponse
import com.diaa.newsfinder.data.remote.newsapi.models.NewsApiResponse

class NewsApiRemoteDataSource(
    private val apiEndpoints: NewsApiEndpoints
) : NewsApiDataSource {
    override suspend fun getNewsApiList(
        q: String,
        from: String,
        sortBy: String
//    ): ApiDefaultResponse<NewsApiGenericResponse<NewsApiResponse>> {
    ): ApiDefaultResponse<NewsApiResponse> {
        return try {
            ApiDefaultResponse.create(apiEndpoints.getNewsList(q, from, sortBy))
        } catch (e: Exception) {
            ApiDefaultResponse.create(e)
        }
    }

    override suspend fun searchBy(
        country: String,
        category: String
//    ): ApiDefaultResponse<NewsApiGenericResponse<NewsApiResponse>> {
    ): ApiDefaultResponse<NewsApiResponse> {
        return try {
            ApiDefaultResponse.create(apiEndpoints.searchBy(country, category))
        } catch (e: Exception) {
            ApiDefaultResponse.create(e)
        }
    }
}