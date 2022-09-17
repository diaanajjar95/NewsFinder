package com.diaa.newsfinder.ui.home

import android.annotation.SuppressLint
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
import java.text.SimpleDateFormat
import java.util.*

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

    private val _message = LiveEvent<String>()
    val message: LiveData<String> = _message

    // newsapi variables
    private var apiQuery: String = "tesla"
    private var fromDate: String = "2022/09/16"
    private var sortBy: String = "publishedAt"

    // newsdata variables
    private var nextPage: Int? = 1
    private var dataQuery: String = "cryptocurrency"

    init {
        getInitData()
    }

    private fun getInitData() {
        _loading.value = true
        viewModelScope.launch {
            val newsApi =
                async { newsApiRepository.getNewsApiList(apiQuery, fromDate, sortBy) }
            val newsData =
                async { newsDataRepository.getNewsDataList(nextPage ?: 1, dataQuery) }

            val newsApiResult = newsApi.await()
            val newsDataResult = newsData.await()

            _loading.value = false
            when (newsApiResult) {
                is ApiDefaultResponse.Success -> {
                    _horizontalItems.value = NewsApiMapper.buildFrom(newsApiResult.body)
                }
                is ApiDefaultResponse.Failed -> {
                    _message.value = newsApiResult.error.localizedMessage
                }
            }

            when (newsDataResult) {
                is ApiDefaultResponse.Success -> {
                    nextPage = newsDataResult.body.nextPage
                    _verticalItems.value = NewsDataMapper.buildFrom(newsDataResult.body)
                }
                is ApiDefaultResponse.Failed -> {
                    _message.value = newsDataResult.error.localizedMessage
                }
            }
        }
    }

    fun searchInNewsApi(searchQuery: String) {
        _loading.value = true
        viewModelScope.launch {
            when (val result =
                newsApiRepository.getNewsApiList(
                    if (searchQuery.isEmpty()) apiQuery else searchQuery,
                    "2022/09/16",
                    sortBy
                )) {
                is ApiDefaultResponse.Success -> {
                    _loading.value = false
                    _horizontalItems.value = NewsApiMapper.buildFrom(result.body)
                }
                is ApiDefaultResponse.Failed -> {
                    _loading.value = false
                    _message.value = result.error.localizedMessage
                }
            }
        }
    }

    fun getNewsDataByPage() {
        viewModelScope.launch {
            when (val result =
                newsDataRepository.getNewsDataList(
                    page = nextPage ?: 1,
                    q = dataQuery
                )) {
                is ApiDefaultResponse.Success -> {
                    nextPage = result.body.nextPage
                    _verticalItems.value = NewsDataMapper.buildFrom(result.body)
                }
                is ApiDefaultResponse.Failed -> {
                    _message.value = result.error.localizedMessage
                }
            }
        }
    }

    fun filterBy(country: String?, category: String?) {
        _loading.value = true
        viewModelScope.launch {
            when (val result =
                newsApiRepository.filterBy(
                    country = country ?: "",
                    category = category ?: ""
                )) {
                is ApiDefaultResponse.Success -> {
                    _loading.value = false
                    _horizontalItems.value = NewsApiMapper.buildFrom(result.body)
                }
                is ApiDefaultResponse.Failed -> {
                    _loading.value = false
                    _message.value = result.error.localizedMessage
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getTodayDate(): String {
        val date = Calendar.getInstance().time
        val sdfDestination = SimpleDateFormat("yyyy/mm/dd")
        return sdfDestination.format(date)
    }

}