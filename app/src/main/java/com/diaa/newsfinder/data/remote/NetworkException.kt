package com.diaa.newsfinder.data.remote

import com.diaa.newsfinder.data.remote.newsapi.NewsApiErrorResponse
import com.diaa.newsfinder.data.remote.newsdata.NewsDataErrorResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NetworkException(private val errSource: String?, private val requestUrl: String?) :
    Exception(), KoinComponent {

    private val moshi: Moshi by inject()
    private var errorMessage: String? = null

    init {
        errSource?.let {
            try {

                requestUrl?.let { url ->
                    errorMessage = if (url.contains(BaseUrlType.NEWS_API.type)) {
                        val jsonAdapter: JsonAdapter<NewsApiErrorResponse> =
                            moshi.adapter(NewsApiErrorResponse::class.java)

                        val response = jsonAdapter.fromJson(errSource)
                        response?.message ?: response?.status
                    } else {
                        val jsonAdapter: JsonAdapter<NewsDataErrorResponse> =
                            moshi.adapter(NewsDataErrorResponse::class.java)

                        val response = jsonAdapter.fromJson(errSource)
                        response?.results?.message ?: response?.status
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun getLocalizedMessage(): String {
        return errorMessage ?: ""
    }
}

