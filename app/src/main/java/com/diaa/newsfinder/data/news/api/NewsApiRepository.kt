package com.diaa.newsfinder.data.news.api

import com.diaa.newsfinder.data.remote.ApiDefaultResponse
import com.diaa.newsfinder.data.remote.newsapi.models.NewsApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsApiRepository(
    private val remoteDataSource: NewsApiDataSource
) : NewsApiDataSource {
    override suspend fun getNewsApiList(
        q: String,
        from: String,
        sortBy: String
    ): ApiDefaultResponse<NewsApiResponse> {
        return withContext(Dispatchers.IO) {
            remoteDataSource.getNewsApiList(q, from, sortBy)
        }
    }

    override suspend fun searchBy(
        country: String,
        category: String
    ): ApiDefaultResponse<NewsApiResponse> {
        return withContext(Dispatchers.IO) {
            remoteDataSource.searchBy(country, category)
        }
    }
}