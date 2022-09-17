package com.diaa.newsfinder.data.remote.newsdata

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Empty

@JsonClass(generateAdapter = true)
data class MetaCode(
    @Json(name = "code")
    val code: Int?,
    @Json(name = "error_message")
    val message: String?,
    @Json(name = "errors") val errors: Any?
)

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "meta") val meta: MetaCode?
)

@JsonClass(generateAdapter = true)
data class NewsDataGenericResponse<T>(
    @Json(name = "data")
    val data: T
)