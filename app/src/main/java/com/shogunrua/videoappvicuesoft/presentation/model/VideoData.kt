package com.shogunrua.videoappvicuesoft.presentation.model

import androidx.compose.runtime.mutableStateOf
import com.shogunrua.videoappvicuesoft.domain.utils.EMPTY_STRING
import com.shogunrua.videoappvicuesoft.domain.utils.INT_0

class VideoData(
    videoFileInit: String = EMPTY_STRING,
    videoCurrentIndexInit: Int = INT_0,
) {
    val videoFile = mutableStateOf(videoFileInit)
    val videoCurrentIndex = mutableStateOf(videoCurrentIndexInit)
}
