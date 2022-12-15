package com.shogunrua.composevideoapp.presentation.model

import androidx.compose.runtime.mutableStateOf
import androidx.media3.common.MediaItem
import com.shogunrua.composevideoapp.domain.model.VideoFilesModel

class ListOfVideosData(
    listOfVideosInit: List<VideoFilesModel> = emptyList(),
    listOfMediaItemsInit: List<MediaItem> = emptyList(),
) {
    val listOfVideos = mutableStateOf(listOfVideosInit)
    val listOfMediaItems = mutableStateOf(listOfMediaItemsInit)
}
