package com.words.nacoss.util

import java.lang.Exception

sealed class Result<out T: Any> {
    data class Success<out T: Any>(val data: T): Result<T>()
    data class Error( val e: Exception): Result<Nothing>()

    override fun toString(): String {
        return when (this){
            is Success<*> -> "Success[data = $data]"
            is Error -> "Error[exception = $e]"
        }
    }
}