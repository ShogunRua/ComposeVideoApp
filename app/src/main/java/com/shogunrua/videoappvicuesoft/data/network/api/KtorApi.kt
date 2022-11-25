package com.shogunrua.videoappvicuesoft.data.network.api

import arrow.core.Either
import com.shogunrua.videoappvicuesoft.data.network.utils.Failure

interface KtorApi<out T> {

    suspend fun execute(): Either<Failure, T>

}

