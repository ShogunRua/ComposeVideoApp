package com.shogunrua.videoappvicuesoft.data.repository

import arrow.core.Either
import arrow.core.flatMap
import com.shogunrua.videoappvicuesoft.data.mapper.VideoFilesMapper
import com.shogunrua.videoappvicuesoft.data.network.api.VideoFilesApi
import com.shogunrua.videoappvicuesoft.data.network.utils.Failure
import com.shogunrua.videoappvicuesoft.domain.model.VideoFilesModel
import com.shogunrua.videoappvicuesoft.domain.repository.VideoFilesRepository
import javax.inject.Inject

class VideoFilesRepositoryImpl @Inject constructor(
    private val videoFilesApi: VideoFilesApi,
    private val videoFilesMapper: VideoFilesMapper,
) : VideoFilesRepository {

    override suspend fun getVideoFiles(): Either<Failure, List<VideoFilesModel>> {
        return videoFilesApi.execute().flatMap { videoFileResponse ->
            videoFilesMapper.map(videoFileResponse)
        }
    }
}
