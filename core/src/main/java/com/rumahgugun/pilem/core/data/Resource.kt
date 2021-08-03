package com.rumahgugun.pilem.core.data


sealed class Resource<T>(val status: Status, val data: T?=null, val message: String? = null) {
    class Success<T>(data: T) : com.rumahgugun.pilem.core.data.Resource<T>(status = Status.SUCCESS,data)
    class Loading<T>(data: T? = null) : com.rumahgugun.pilem.core.data.Resource<T>(Status.LOADING,data)
    class Error<T>(message: String, data: T? = null) : com.rumahgugun.pilem.core.data.Resource<T>(
        Status.ERROR, data, message)
}
