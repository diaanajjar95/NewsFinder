package com.diaa.newsfinder.ui.mappers

import com.diaa.newsfinder.changeDateFormatTo
import com.diaa.newsfinder.data.news.api.models.NewsApiResponse
import com.diaa.newsfinder.ui.home.models.HorizontalNewsItem

object NewsApiMapper {

    fun buildFrom(response: NewsApiResponse): MutableList<HorizontalNewsItem> {
        val list = response.articles?.map {
            HorizontalNewsItem(
                imageUrl = it?.urlToImage,
                title = it?.title,
                author = it?.author,
                postDate = it?.publishedAt?.changeDateFormatTo("yyyy-MM-dd'T'HH:mm:ss'Z'"),
                url = it?.url
            )
        }?.toMutableList()
        return list ?: mutableListOf()
    }

//    @SuppressLint("SimpleDateFormat")
//    private fun formatDate(stringDate: String): String {
//        val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
//        val output = SimpleDateFormat("dd/MM/yyyy")
//        return try {
//            output.format(input.parse(stringDate) as Date)
//        } catch (e: ParseException) {
//            e.printStackTrace()
//            ""
//        }
//    }
}