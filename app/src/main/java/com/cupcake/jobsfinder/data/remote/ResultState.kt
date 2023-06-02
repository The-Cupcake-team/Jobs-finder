package com.cupcake.jobsfinder.data.remote

sealed class ResultState<out T> {
    object Loading : ResultState<Nothing>()
    class Success<T>(val response: T) : ResultState<T>()
    class Error(val exception: Throwable) : ResultState<Nothing>()
}