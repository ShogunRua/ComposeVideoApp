package com.shogunrua.videoappvicuesoft.presentation.model

import com.shogunrua.videoappvicuesoft.data.network.utils.Failure

sealed class VideoPlayerUiState {
    data class LoadComplete(val uiData: VideoPlayerCombineData) : VideoPlayerUiState()
    object LoadInProgress : VideoPlayerUiState()
    data class Error(val failure: Failure) : VideoPlayerUiState()
}
