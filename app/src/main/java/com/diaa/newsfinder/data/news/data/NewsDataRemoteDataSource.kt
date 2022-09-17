package com.diaa.newsfinder.data.news.data

import com.diaa.newsfinder.data.news.data.models.NewsDataResponse
import com.diaa.newsfinder.data.remote.ApiDefaultResponse
import com.diaa.newsfinder.data.remote.newsdata.NewsDataEndpoints

class NewsDataRemoteDataSource(
    private val apiEndpoints: NewsDataEndpoints
) : NewsDataDataSource {
    override suspend fun getNewsDataList(
        page: Int,
        q: String
    ): ApiDefaultResponse<NewsDataResponse> {
        return try {
            ApiDefaultResponse.create(apiEndpoints.getNewsList(page, q))
        } catch (e: Exception) {
            ApiDefaultResponse.create(e)
        }
    }
}