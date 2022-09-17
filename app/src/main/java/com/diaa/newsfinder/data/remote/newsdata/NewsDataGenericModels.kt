package com.diaa.newsfinder.data.remote.newsdata

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Empty

@JsonClass(generateAdapter = true)
data class NewsDataErrorResponse(
    @Json(name = "results")
    val results: ErrorResults?,
    @Json(name = "status")
    val status: String?
)

@JsonClass(generateAdapter = true)
data class ErrorResults(
    @Json(name = "code")
    val code: String?,
    @Json(name = "message")
    val message: String?
)

@JsonClass(generateAdapter = true)
data class NewsDataGenericResponse<T>(
    @Json(name = "data")
    val data: T
)