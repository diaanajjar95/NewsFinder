package com.diaa.newsfinder.ui.mappers

import com.diaa.newsfinder.changeDateFormatFrom
import com.diaa.newsfinder.data.news.api.models.NewsApiResponse
import com.diaa.newsfinder.ui.home.models.HorizontalNewsItem

object NewsApiMapper {

    fun buildFrom(response: NewsApiResponse): MutableList<HorizontalNewsItem> {
        val list = response.articles?.map {
            HorizontalNewsItem(
                imageUrl = it?.urlToImage,
                title = it?.title,
                author = it?.author,
                postDate = it?.publishedAt?.changeDateFormatFrom("yyyy-MM-dd'T'HH:mm:ss'Z'"),
                url = it?.url
            )
        }?.toMutableList()
        return list ?: mutableListOf()
    }
}