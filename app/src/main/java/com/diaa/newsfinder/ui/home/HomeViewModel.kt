package com.diaa.newsfinder.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diaa.newsfinder.data.news.api.NewsApiDataSource
import com.diaa.newsfinder.data.news.data.NewsDataDataSource
import com.diaa.newsfinder.data.remote.ApiDefaultResponse
import com.diaa.newsfinder.ui.home.models.HorizontalNewsItem
import com.diaa.newsfinder.ui.home.models.VerticalNewsItem
import com.diaa.newsfinder.ui.mappers.NewsApiMapper
import com.diaa.newsfinder.ui.mappers.NewsDataMapper
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeViewModel(
    private val newsApiRepository: NewsApiDataSource,
    private val newsDataRepository: NewsDataDataSource,
) : ViewModel() {

    private val _horizontalItems: MutableLiveData<MutableList<HorizontalNewsItem>> =
        MutableLiveData()
    val horizontalItems: LiveData<MutableList<HorizontalNewsItem>> = _horizontalItems

    private val _verticalItems: MutableLiveData<MutableList<VerticalNewsItem>> = MutableLiveData()
    val verticalItems: LiveData<MutableList<VerticalNewsItem>> = _verticalItems

    private val _loading = LiveEvent<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private var nextPage: Int? = 1

    fun getInitData() {
        _loading.value = true
        viewModelScope.launch {
            val newsApi =
                async { newsApiRepository.getNewsApiList("tesla", "2022-08-16", "publishedAt") }
            val newsData =
                async { newsDataRepository.getNewsDataList(nextPage ?: 1, "cryptocurrency") }

            val newsApiResult = newsApi.await()
            val newsDataResult = newsData.await()


            _loading.value = false
            when (newsApiResult) {
                is ApiDefaultResponse.Success -> {
                    _horizontalItems.value = NewsApiMapper.buildFrom(newsApiResult.body)
                }
                is ApiDefaultResponse.Failed -> {

                }
                is ApiDefaultResponse.Empty -> {

                }
            }

            when (newsDataResult) {
                is ApiDefaultResponse.Success -> {
                    nextPage = newsDataResult.body.nextPage
                    _verticalItems.value = NewsDataMapper.buildFrom(newsDataResult.body)
                }
                is ApiDefaultResponse.Failed -> {

                }
                is ApiDefaultResponse.Empty -> {

                }
            }
        }
    }

    fun getNewsDataByPage() {
        viewModelScope.launch {
            when (val result =
                newsDataRepository.getNewsDataList(
                    page = nextPage ?: 1,
                    q = "cryptocurrency"
                )) {
                is ApiDefaultResponse.Success -> {
                    nextPage = result.body.nextPage
                    _verticalItems.value = NewsDataMapper.buildFrom(result.body)
                }
                is ApiDefaultResponse.Failed -> {

                }
                is ApiDefaultResponse.Empty -> {

                }
            }
        }
    }


}