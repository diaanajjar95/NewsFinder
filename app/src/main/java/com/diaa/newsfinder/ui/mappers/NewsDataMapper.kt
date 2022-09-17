package com.diaa.newsfinder.ui.mappers

import com.diaa.newsfinder.changeDateFormatFrom
import com.diaa.newsfinder.data.news.data.models.NewsDataResponse
import com.diaa.newsfinder.ui.home.models.VerticalNewsItem

object NewsDataMapper {

    fun buildFrom(response: NewsDataResponse): MutableList<VerticalNewsItem> {
        val list = response.results?.map {
            VerticalNewsItem(
                imageUrl = it?.imageUrl,
                title = it?.title,
                author = it?.creator?.firstOrNull() ?: "anonymous",
                postDate = it?.pubDate?.changeDateFormatFrom("yyyy-mm-dd hh:mm:ss"),
                url = it?.link
            )
        }?.toMutableList()
        return list ?: mutableListOf()
    }
}