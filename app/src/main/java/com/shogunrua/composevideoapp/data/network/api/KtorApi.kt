package com.shogunrua.composevideoapp.data.network.api

import arrow.core.Either
import com.shogunrua.composevideoapp.data.network.utils.Failure

interface KtorApi<out T> {

    suspend fun execute(): Either<Failure, T>

}

