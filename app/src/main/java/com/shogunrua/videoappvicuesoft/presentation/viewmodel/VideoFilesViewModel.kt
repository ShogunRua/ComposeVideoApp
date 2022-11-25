package com.shogunrua.videoappvicuesoft.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shogunrua.videoappvicuesoft.domain.model.VideoFilesModel
import com.shogunrua.videoappvicuesoft.domain.usecase.GetVideoFilesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoFilesViewModel @Inject constructor(
    private val getVideoFilesUseCase: GetVideoFilesUseCase,
) : ViewModel() {

    private val videoFilesList = mutableStateOf<List<VideoFilesModel>>(emptyList())

    init {
        getVideoFiles()
    }

    private fun getVideoFiles() {
        viewModelScope.launch(Dispatchers.IO) {
            getVideoFilesUseCase.getVideoFiles().fold(
                ifLeft = {
                    Log.d("Failure", "getVideoFilesFail")
                },
                ifRight = {
                    videoFilesList.value = it
                    Log.d("VideoFilesViewModel", "getVideoFiles: $it")
                }
            )
        }
    }
}
