package com.eddiej.searchbeers.domain.model

sealed class Result<T> {
    class Success<T>(val data: T): Result<T>()

    object Loading : Result<Nothing>()

    class ApiError<T>(val message: String): Result<T>()

    class NetworkError<T>(val throwable: Throwable): Result<T>()

    class NullResult<T>: Result<T>()
}
