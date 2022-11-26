package com.shogunrua.videoappvicuesoft.presentation.model

import com.shogunrua.videoappvicuesoft.domain.model.VideoFilesModel

data class VideoDataCallbacks(
    val clickItem: (VideoFilesModel) -> Unit = {},
)
