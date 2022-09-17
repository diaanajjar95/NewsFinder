package com.diaa.newsfinder

import android.app.Application
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import com.diaa.newsfinder.data.news.api.repoNewsApiModule
import com.diaa.newsfinder.data.news.data.repoNewsDataModule
import com.diaa.newsfinder.data.remote.remoteModule
import com.diaa.newsfinder.ui.base.bindingadapters.BindingAdapters
import com.diaa.newsfinder.ui.home.homeModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppApplication : Application(), DataBindingComponent {

    private val appBindingAdapters: BindingAdapters by inject()

    override fun onCreate() {
        super.onCreate()
        DataBindingUtil.setDefaultComponent(this)
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@AppApplication)
            modules(
                listOf(
                    appModule,
                    remoteModule,
                    repoNewsApiModule,
                    repoNewsDataModule,
                    homeModule
                )
            )
        }

    }

    override fun getBindingAdapters(): BindingAdapters {
        return appBindingAdapters
    }

}