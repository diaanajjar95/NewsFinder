package com.diaa.newsfinder.ui.mappers

import com.diaa.newsfinder.data.remote.newsdata.models.NewsDataResponse
import com.diaa.newsfinder.ui.home.models.VerticalNewsItem

object NewsDataMapper {

    fun buildFrom(response: NewsDataResponse): MutableList<VerticalNewsItem> {
        val list = response.results?.map {
            VerticalNewsItem(
                imageUrl = it?.imageUrl,
                title = it?.title,
                author = it?.title,
                postDate = it?.pubDate
            )
        }?.toMutableList()
        return list ?: mutableListOf()
    }

}