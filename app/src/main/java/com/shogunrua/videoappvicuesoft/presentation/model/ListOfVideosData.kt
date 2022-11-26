package com.shogunrua.videoappvicuesoft.presentation.model

import androidx.compose.runtime.mutableStateOf
import com.shogunrua.videoappvicuesoft.domain.model.VideoFilesModel

class ListOfVideosData(
    listOfVideosInit: List<VideoFilesModel> = emptyList(),
) {
    val listOfVideos = mutableStateOf(listOfVideosInit)
}
