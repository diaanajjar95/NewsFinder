package com.diaa.newsfinder

import com.diaa.newsfinder.ui.base.bindingadapters.BindingAdapters
import com.diaa.newsfinder.ui.base.bindingadapters.BindingAdaptersImpl
import org.koin.dsl.module

val appModule = module {

    single<BindingAdapters> {
        BindingAdaptersImpl()
    }

}