package com.vastausf.wesolient.data


sealed class Bucket<T> {
    class Initial<T> : Bucket<T>()

    class Loading<T> : Bucket<T>()

    class Empty<T> : Bucket<T>()

    class Data<T>(data: T) : Bucket<T>()

    class Error<T>(error: Throwable) : Bucket<T>()

    val isInitial: Boolean
        get() = this is Initial

    val isLoading: Boolean
        get() = this is Loading

    val isEmpty: Boolean
        get() = this is Empty

    val isData: Boolean
        get() = this is Data

    val isError: Boolean
        get() = this is Error
}
