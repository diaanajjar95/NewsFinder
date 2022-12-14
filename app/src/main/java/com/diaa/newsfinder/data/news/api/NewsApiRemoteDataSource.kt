package com.diaa.newsfinder.data.news.api

import com.diaa.newsfinder.data.news.api.models.NewsApiResponse
import com.diaa.newsfinder.data.remote.ApiDefaultResponse
import com.diaa.newsfinder.data.remote.newsapi.NewsApiEndpoints

class NewsApiRemoteDataSource(
    private val apiEndpoints: NewsApiEndpoints
) : NewsApiDataSource {
    override suspend fun getNewsApiList(
        q: String,
        from: String,
        sortBy: String
    ): ApiDefaultResponse<NewsApiResponse> {
        return try {
            ApiDefaultResponse.create(apiEndpoints.getNewsList(q, from, sortBy))
        } catch (e: Exception) {
            ApiDefaultResponse.create(e)
        }
    }

    override suspend fun filterBy(
        country: String,
        category: String
    ): ApiDefaultResponse<NewsApiResponse> {
        return try {
            ApiDefaultResponse.create(apiEndpoints.filterBy(country, category))
        } catch (e: Exception) {
            ApiDefaultResponse.create(e)
        }
    }
}