package com.diaa.newsfinder.data.remote.newsdata.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsDataResponse(
    @Json(name = "nextPage")
    val nextPage: Int?,
    @Json(name = "results")
    val results: List<Result?>?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "totalResults")
    val totalResults: Int?
)