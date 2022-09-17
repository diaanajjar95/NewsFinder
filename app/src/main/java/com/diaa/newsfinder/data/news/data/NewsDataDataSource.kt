package com.diaa.newsfinder.data.news.data

import com.diaa.newsfinder.data.news.data.models.NewsDataResponse
import com.diaa.newsfinder.data.remote.ApiDefaultResponse

interface NewsDataDataSource {

    suspend fun getNewsDataList(
        page: Int,
        q: String
    ): ApiDefaultResponse<NewsDataResponse>

}