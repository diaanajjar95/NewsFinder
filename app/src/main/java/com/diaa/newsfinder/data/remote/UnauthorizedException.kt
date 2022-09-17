package com.diaa.newsfinder.data.remote

import com.squareup.moshi.Moshi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UnauthorizedException(private val errSource: String?) : Exception(),
    KoinComponent {
    // Lazy inject to prevent recursive injection
    private val moshi: Moshi by inject()
    private var validationMessage: String? = null

    init {
        errSource?.let {
            try {
//                val jsonAdapter: JsonAdapter<Response401> =
//                    moshi.adapter(Response401::class.java)

//                val response = jsonAdapter.fromJson(errSource)
//                validationMessage = response?.msg ?: response?.error?.message

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun getLocalizedMessage(): String {
        return validationMessage ?: ""
    }
}

