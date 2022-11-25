package com.shogunrua.videoappvicuesoft.data.utils

import android.util.Log
import arrow.core.Either
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiCallWrapper @Inject constructor() {

    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        apiCall: suspend () -> T,
    ): Either<Failure, T> {

        return withContext(dispatcher) {
            Either.catch {
                apiCall.invoke()
            }.mapLeft {
                Log.e(ApiCallWrapper::class.simpleName, it.stackTraceToString())
                responseExceptionError()
            }
        }
    }

    private fun responseExceptionError() = Failure
}
