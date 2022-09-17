package com.diaa.newsfinder.ui.mappers

import com.diaa.newsfinder.changeDateFormatTo
import com.diaa.newsfinder.data.news.data.models.NewsDataResponse
import com.diaa.newsfinder.ui.home.models.VerticalNewsItem

object NewsDataMapper {

    fun buildFrom(response: NewsDataResponse): MutableList<VerticalNewsItem> {
        val list = response.results?.map {
            VerticalNewsItem(
                imageUrl = it?.imageUrl,
                title = it?.title,
                author = it?.creator?.firstOrNull() ?: "anonymous",
                postDate = it?.pubDate?.changeDateFormatTo("yyyy-mm-dd hh:mm:ss"),
                url = it?.link
            )
        }?.toMutableList()
        return list ?: mutableListOf()
    }

//    @SuppressLint("SimpleDateFormat")
//    private fun formatDate(stringDate: String): String {
//        val input = SimpleDateFormat("yyyy-mm-dd hh:mm:ss")
//        val output = SimpleDateFormat("dd/MM/yyyy")
//        return try {
//            output.format(input.parse(stringDate) as Date)
//        } catch (e: ParseException) {
//            e.printStackTrace()
//            ""
//        }
//    }

}