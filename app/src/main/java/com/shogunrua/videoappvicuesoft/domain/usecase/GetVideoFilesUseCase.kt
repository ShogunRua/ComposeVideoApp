package com.shogunrua.videoappvicuesoft.domain.usecase

import arrow.core.Either
import com.shogunrua.videoappvicuesoft.data.network.utils.Failure
import com.shogunrua.videoappvicuesoft.domain.model.VideoFilesModel
import com.shogunrua.videoappvicuesoft.domain.repository.VideoFilesRepository
import javax.inject.Inject

class GetVideoFilesUseCase @Inject constructor(
    private val repository: VideoFilesRepository,
) {

    suspend fun getVideoFiles(): Either<Failure, List<VideoFilesModel>> {
        return repository.getVideoFiles()
    }

}
