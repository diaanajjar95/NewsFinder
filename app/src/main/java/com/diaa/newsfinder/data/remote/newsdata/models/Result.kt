package com.diaa.newsfinder.data.remote.newsdata.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "category")
    val category: List<String?>?,
    @Json(name = "content")
    val content: String?,
    @Json(name = "country")
    val country: List<String?>?,
    @Json(name = "creator")
    val creator: List<String?>?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "full_description")
    val fullDescription: String?,
    @Json(name = "image_url")
    val imageUrl: String?,
    @Json(name = "keywords")
    val keywords: List<String?>?,
    @Json(name = "language")
    val language: String?,
    @Json(name = "link")
    val link: String?,
    @Json(name = "pubDate")
    val pubDate: String?,
    @Json(name = "source_id")
    val sourceId: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "video_url")
    val videoUrl: String?
)