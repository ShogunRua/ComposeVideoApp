package com.shogunrua.composevideoapp.domain.usecase

import arrow.core.Either
import com.shogunrua.composevideoapp.data.network.utils.Failure
import com.shogunrua.composevideoapp.domain.model.VideoFilesModel
import com.shogunrua.composevideoapp.domain.repository.VideoFilesRepository
import javax.inject.Inject

class GetVideoFilesUseCase @Inject constructor(
    private val repository: VideoFilesRepository,
) {

    suspend fun getVideoFiles(): Either<Failure, List<VideoFilesModel>> {
        return repository.getVideoFiles()
    }

}
