package com.diaa.newsfinder.data.news.data

import org.koin.core.qualifier.named
import org.koin.dsl.module

internal const val KOIN_NAME_NEWS_DATA_REMOTE_DATA_SOURCE = "KOIN_NAME_NEWS_DATA_REMOTE_DATA_SOURCE"

val repoNewsDataModule = module {

    single<NewsDataDataSource> {
        NewsDataRepository(get(named(KOIN_NAME_NEWS_DATA_REMOTE_DATA_SOURCE)))
    }

    single<NewsDataDataSource>(named(KOIN_NAME_NEWS_DATA_REMOTE_DATA_SOURCE)) {
        NewsDataRemoteDataSource(get())
    }

}