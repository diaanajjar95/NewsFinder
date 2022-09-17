package com.diaa.newsfinder.ui.home

import com.diaa.newsfinder.ui.home.adapters.HorizontalNewsAdapter
import com.diaa.newsfinder.ui.home.adapters.VerticalNewsAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get(), get()) }
    factory { HorizontalNewsAdapter() }
    factory { VerticalNewsAdapter() }
}