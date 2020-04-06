package com.words.nacoss.util

import com.words.nacoss.util.Result.*
import java.io.IOException
import java.lang.Exception

suspend fun <T: Any>safeApiCall(error: String, call: suspend () -> Result<T>) : Result<T>{
    return try {
        call()
    }catch (e: Exception){
        return Error(IOException(error,e))
    }
}