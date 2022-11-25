package com.shogunrua.videoappvicuesoft.domain.repository

import arrow.core.Either
import com.shogunrua.videoappvicuesoft.data.network.utils.Failure
import com.shogunrua.videoappvicuesoft.domain.model.VideoFilesModel


interface VideoFilesRepository {

    suspend fun getVideoFiles(): Either<Failure, List<VideoFilesModel>>
}
