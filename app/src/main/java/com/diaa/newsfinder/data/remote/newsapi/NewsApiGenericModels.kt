package com.diaa.newsfinder.data.remote.newsapi

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Empty

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "status")
    val status: String?,
    @Json(name = "code")
    val code: String?,
    @Json(name = "message")
    val message: String?
)

@JsonClass(generateAdapter = true)
data class NewsApiGenericResponse<T>(
    @Json(name = "data")
    val data: T
)
