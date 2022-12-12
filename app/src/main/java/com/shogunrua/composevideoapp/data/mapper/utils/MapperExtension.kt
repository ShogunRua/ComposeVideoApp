package com.shogunrua.composevideoapp.data.mapper.utils

import arrow.core.Either
import com.shogunrua.composevideoapp.data.network.utils.Failure

interface MapperExtension<in I, out O> {

    fun map(target: I): Either<Failure, O>

}
