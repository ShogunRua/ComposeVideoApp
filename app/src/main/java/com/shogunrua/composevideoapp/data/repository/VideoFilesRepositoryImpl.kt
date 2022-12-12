package com.shogunrua.composevideoapp.data.repository

import arrow.core.Either
import arrow.core.flatMap
import com.shogunrua.composevideoapp.data.mapper.VideoFilesMapper
import com.shogunrua.composevideoapp.data.network.api.VideoFilesApi
import com.shogunrua.composevideoapp.data.network.utils.Failure
import com.shogunrua.composevideoapp.domain.model.VideoFilesModel
import com.shogunrua.composevideoapp.domain.repository.VideoFilesRepository
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
