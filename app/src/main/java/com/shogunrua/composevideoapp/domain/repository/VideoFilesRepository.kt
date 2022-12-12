package com.shogunrua.composevideoapp.domain.repository

import arrow.core.Either
import com.shogunrua.composevideoapp.data.network.utils.Failure
import com.shogunrua.composevideoapp.domain.model.VideoFilesModel


interface VideoFilesRepository {

    suspend fun getVideoFiles(): Either<Failure, List<VideoFilesModel>>
}
