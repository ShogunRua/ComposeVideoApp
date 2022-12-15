package com.shogunrua.composevideoapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import com.shogunrua.composevideoapp.domain.model.VideoFilesModel
import com.shogunrua.composevideoapp.domain.usecase.VideoFilesUseCases
import com.shogunrua.composevideoapp.presentation.model.ListOfVideosData
import com.shogunrua.composevideoapp.presentation.model.VideoData
import com.shogunrua.composevideoapp.presentation.model.VideoPlayerCombineData
import com.shogunrua.composevideoapp.presentation.model.uiState.VideoPlayerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoFilesViewModel @Inject constructor(
    private val videoFilesUseCases: VideoFilesUseCases,
) : ViewModel() {

    private var _uiState =
        MutableStateFlow<VideoPlayerUiState>(VideoPlayerUiState.LoadInProgress)
    val uiState: StateFlow<VideoPlayerUiState> = _uiState.asStateFlow()

    private val listOfVideosData = MutableStateFlow(ListOfVideosData())
    private val videosData = MutableStateFlow(VideoData())

    init {
        getVideoFiles()
    }

    private fun getVideoFiles() {
        viewModelScope.launch {
            videoFilesUseCases.getVideoFilesUseCase.getVideoFiles().fold(
                ifLeft = {
                    Log.d("Failure", "getVideoFilesFail")
                },
                ifRight = {
                    listOfVideosData.value.listOfVideos.value = it
                    videosData.value.videoFile.value =
                        listOfVideosData.value.listOfVideos.value.first().fileUrl
                    listOfVideosData.value.listOfMediaItems.value = getMediaItemsList(it)
                    buildVideoFilesUiData()
                    Log.d("VideoFilesViewModel", "getVideoFiles: $it")
                }
            )
        }
    }

    private fun getMediaItemsList(listOfVideos: List<VideoFilesModel>): List<MediaItem> {
        return videoFilesUseCases.getMediaItemsUseCase.getMediaItemsList(listOfVideos)
    }

    private fun buildVideoFilesUiData() {
        viewModelScope.launch(Dispatchers.IO) {
            combine(
                listOfVideosData,
                videosData,
            ) { listOfVideosData, videosData ->
                VideoPlayerCombineData(
                    listOfVideosData = listOfVideosData,
                    videoData = videosData,
                )
            }.collect { videoFilesUiData ->
                _uiState.value = VideoPlayerUiState.LoadComplete(videoFilesUiData)
            }
        }
    }
}
