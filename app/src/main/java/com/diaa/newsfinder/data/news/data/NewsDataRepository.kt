package com.diaa.newsfinder.data.news.data

import com.diaa.newsfinder.data.news.data.models.NewsDataResponse
import com.diaa.newsfinder.data.remote.ApiDefaultResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsDataRepository(
    private val remoteDataSource: NewsDataDataSource
) : NewsDataDataSource {
    override suspend fun getNewsDataList(
        page: Int,
        q: String
    ): ApiDefaultResponse<NewsDataResponse> {
        return withContext(Dispatchers.IO) {
            remoteDataSource.getNewsDataList(page, q)
        }
    }
}