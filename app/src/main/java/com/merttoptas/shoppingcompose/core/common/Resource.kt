package com.merttoptas.shoppingcompose.core.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

/**
 * Created by mertcantoptas on 26.05.2023
 */

sealed interface Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>
    data class Error(val exception: Throwable? = null) : Resource<Nothing>
    object Loading : Resource<Nothing>
}

fun <T> Flow<T>.asResource(): Flow<Resource<T>> {
    return this
        .map<T, Resource<T>> {
            Resource.Success(it)
        }
        .onStart { emit(Resource.Loading) }
        .catch { emit(Resource.Error(it)) }
}