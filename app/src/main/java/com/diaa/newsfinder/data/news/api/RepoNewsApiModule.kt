package com.diaa.newsfinder.data.news.api

import org.koin.core.qualifier.named
import org.koin.dsl.module

internal const val KOIN_NAME_NEWS_API_REPOSITORY = "KOIN_NAME_NEWS_API_REPOSITORY"
internal const val KOIN_NAME_NEWS_API_REMOTE_DATA_SOURCE = "KOIN_NAME_NEWS_API_REMOTE_DATA_SOURCE"

val repoNewsApiModule = module {

    single<NewsApiDataSource>/*(named(KOIN_NAME_NEWS_API_REPOSITORY))*/ {
        NewsApiRepository(get(named(KOIN_NAME_NEWS_API_REMOTE_DATA_SOURCE)))
    }

    single<NewsApiDataSource>(named(KOIN_NAME_NEWS_API_REMOTE_DATA_SOURCE)) {
        NewsApiRemoteDataSource(get())
    }

}