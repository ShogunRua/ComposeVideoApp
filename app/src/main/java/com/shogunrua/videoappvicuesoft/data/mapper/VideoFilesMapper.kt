package com.shogunrua.videoappvicuesoft.data.mapper

import arrow.core.Either
import com.shogunrua.videoappvicuesoft.data.mapper.utils.MapperExtension
import com.shogunrua.videoappvicuesoft.data.network.response.VideoFilesResponse
import com.shogunrua.videoappvicuesoft.data.network.utils.Failure
import com.shogunrua.videoappvicuesoft.domain.model.VideoFilesModel
import com.shogunrua.videoappvicuesoft.domain.utils.EMPTY_STRING
import javax.inject.Inject

class VideoFilesMapper @Inject constructor() :
    MapperExtension<VideoFilesResponse, List<VideoFilesModel>> {

    override fun map(target: VideoFilesResponse): Either<Failure, List<VideoFilesModel>> {
        val exchangeRatesList = mutableListOf<VideoFilesModel>()
        target.videoFiles.forEach { videoFilesItem ->
            exchangeRatesList.add(
                VideoFilesModel(
                    id = videoFilesItem.id,
                    color = videoFilesItem.color ?: EMPTY_STRING,
                    fileUrl = videoFilesItem.fileUrl,
                    group = videoFilesItem.group,
                    isFavorite = videoFilesItem.isFavorite,
                    posterUrl = videoFilesItem.posterUrl,
                    smallPosterUrl = videoFilesItem.smallPosterUrl,
                    smallThumbnailUrl = videoFilesItem.smallThumbnailUrl,
                    thumbnailUrl = videoFilesItem.thumbnailUrl,
                )
            )
        }
        return Either.catch {
            exchangeRatesList
        }.mapLeft {
            Failure
        }
    }
}
