package com.shogunrua.videoappvicuesoft.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shogunrua.videoappvicuesoft.presentation.model.VideoPlayerCombineData
import com.shogunrua.videoappvicuesoft.presentation.model.uiState.VideoPlayerUiState
import com.shogunrua.videoappvicuesoft.presentation.viewmodel.VideoFilesViewModel

@Composable
fun VideoPlayerScreen(
    viewModel: VideoFilesViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    var inProgress by remember { mutableStateOf(true) }

    FullScreenProgressIndicator(
        isLoading = inProgress,
    )

    VideoPlayerContentHandler(
        uiState = uiState,
        progressState = {
            inProgress = it
        },
    )
}

@Composable
fun VideoPlayerContentHandler(
    uiState: VideoPlayerUiState,
    progressState: (Boolean) -> Unit,
) {
    when (uiState) {
        is VideoPlayerUiState.LoadComplete -> {
            progressState.invoke(false)
            VideoPlayerContent(
                data = uiState.uiData
            )
        }

        is VideoPlayerUiState.LoadInProgress -> {
            progressState.invoke(true)
        }

        is VideoPlayerUiState.Error -> {
            progressState.invoke(false)
        }
    }
}

@Composable
fun VideoPlayerContent(
    data: VideoPlayerCombineData,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.padding(20.dp))

        VideoPlayerBlock(
            videoData = data.videoData,
            listOfVideosData = data.listOfVideosData,
        )

        Spacer(modifier = modifier.padding(16.dp))

        VideoIconsBlock(
            videoData = data.videoData,
            listOfVideosData = data.listOfVideosData,
        )
    }
}
