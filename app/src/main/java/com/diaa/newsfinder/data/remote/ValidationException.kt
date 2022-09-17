package com.diaa.newsfinder.data.remote

import com.squareup.moshi.Moshi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ValidationException(errSource: String? = null) : Exception(), KoinComponent {
    // Lazy inject to prevent recursive injection
    private val moshi: Moshi by inject()

}