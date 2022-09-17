package com.diaa.newsfinder.ui.mappers

import com.diaa.newsfinder.data.remote.newsapi.models.NewsApiResponse
import com.diaa.newsfinder.ui.home.models.HorizontalNewsItem

object NewsApiMapper {

    fun buildFrom(response: NewsApiResponse): MutableList<HorizontalNewsItem> {
        val list = response.articles?.map {
            HorizontalNewsItem(
                imageUrl = it?.urlToImage,
                title = it?.title,
                author = it?.author,
                postDate = it?.publishedAt
            )
        }?.toMutableList()
        return list ?: mutableListOf()
    }

}