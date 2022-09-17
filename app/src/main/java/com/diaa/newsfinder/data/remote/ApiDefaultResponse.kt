package com.diaa.newsfinder.data.remote

import retrofit2.Response

sealed class ApiDefaultResponse<T> {

    data class Failed<T>(val error: Throwable) : ApiDefaultResponse<T>()
    data class Success<T>(val body: T) : ApiDefaultResponse<T>()
    class Empty<T> : ApiDefaultResponse<T>()

    companion object {

        fun <T> create(response: Response<T>): ApiDefaultResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                Success(body ?: throw EmptyResponseException())

            } else
                when (response.code()) {
                    401 -> {
                        try {
                            val err = response.errorBody()
                            Failed(
                                NetworkException(
                                    err?.string(),
                                    response.raw().request.url.toString()
                                )
                            )
                        } catch (ex: Exception) {
                            Failed(Exception())
                        }
                    }
                    in 400..499 -> {
                        try {
                            val err = response.errorBody()
                            Failed(
                                NetworkException(
                                    err?.string(),
                                    response.raw().request.url.toString()
                                )
                            )
                        } catch (ex: Exception) {
                            Failed(Exception())
                        }
                    }
                    500 -> {
                        Failed(Exception())
                    }
                    else -> {
                        Failed(Exception())
                    }
                }
        }

        fun <T> create(error: Throwable): Failed<T> {
            return Failed(error)
        }

    }

}

