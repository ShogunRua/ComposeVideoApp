package com.shogunrua.composevideoapp.domain.usecase

import androidx.media3.common.MediaItem
import com.shogunrua.composevideoapp.domain.model.VideoFilesModel
import javax.inject.Inject

class GetMediaItemsUseCase @Inject constructor() {

    fun getMediaItemsList(listOfVideos: List<VideoFilesModel>): List<MediaItem> {
        val mediaItemList = mutableListOf<MediaItem>()
        listOfVideos.forEach {
            mediaItemList.add(MediaItem.fromUri(it.fileUrl))
        }
        return mediaItemList
    }

}
