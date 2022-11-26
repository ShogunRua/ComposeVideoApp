package com.shogunrua.videoappvicuesoft.presentation.model.uiState

import com.shogunrua.videoappvicuesoft.data.network.utils.Failure
import com.shogunrua.videoappvicuesoft.presentation.model.VideoPlayerCombineData

sealed class VideoPlayerUiState {
    data class LoadComplete(val uiData: VideoPlayerCombineData) : VideoPlayerUiState()
    object LoadInProgress : VideoPlayerUiState()
    data class Error(val failure: Failure) : VideoPlayerUiState()
}
