package com.diaa.newsfinder.data.remote.newsapi.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Source(
    @Json(name = "id")
    val id: Any?,
    @Json(name = "name")
    val name: String?
)