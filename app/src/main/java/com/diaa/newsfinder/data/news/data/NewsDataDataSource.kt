package com.diaa.newsfinder.data.news.data

import com.diaa.newsfinder.data.remote.ApiDefaultResponse
import com.diaa.newsfinder.data.remote.newsdata.NewsDataGenericResponse
import com.diaa.newsfinder.data.remote.newsdata.models.NewsDataResponse
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsDataDataSource {

    suspend fun getNewsDataList(
        page: Int,
        q: String
//    ): ApiDefaultResponse<NewsDataGenericResponse<NewsDataResponse>>
    ): ApiDefaultResponse<NewsDataResponse>

}