package com.diaa.newsfinder.data.news.data

import com.diaa.newsfinder.data.remote.ApiDefaultResponse
import com.diaa.newsfinder.data.remote.newsdata.models.NewsDataResponse

interface NewsDataDataSource {

    suspend fun getNewsDataList(
        page: Int,
        q: String
    ): ApiDefaultResponse<NewsDataResponse>

}