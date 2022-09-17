package com.diaa.newsfinder.ui.home

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get(), get()) }
    factory { HorizontalNewsAdapter() }
    factory { VerticalNewsAdapter() }
}