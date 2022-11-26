package com.shogunrua.videoappvicuesoft.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shogunrua.videoappvicuesoft.R
import com.shogunrua.videoappvicuesoft.presentation.model.VideoDataCallbacks
import com.shogunrua.videoappvicuesoft.presentation.model.VideoPlayerCombineData
import com.shogunrua.videoappvicuesoft.presentation.model.VideoPlayerUiState
import com.shogunrua.videoappvicuesoft.presentation.theme.GrayBackGround
import com.shogunrua.videoappvicuesoft.presentation.theme.PurpleButton
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
        videoDataCallbacks = VideoDataCallbacks(
            clickItem = {

            }
        )
    )
}

@Composable
fun VideoPlayerContentHandler(
    uiState: VideoPlayerUiState,
    videoDataCallbacks: VideoDataCallbacks,
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

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.padding(20.dp))

        if (!data.videoData.videoIsReady.value) {
            ExoVideoPlayer(data = data.videoData)
        }
        Spacer(modifier = modifier.padding(20.dp))

        Button(
            onClick = {

            },
            modifier = modifier
                .size(width = 150.dp, height = 50.dp),
            shape = RoundedCornerShape(6.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = PurpleButton)
        ) {
            Text(
                text = stringResource(id = R.string.button_text),
                color = Color.White,
                fontSize = 16.sp,
            )
        }

        Spacer(modifier = modifier.padding(16.dp))

        LazyRow(
            modifier = modifier
                .background(color = GrayBackGround)
                .selectableGroup(),
        ) {
            itemsIndexed(data.listOfVideosData.listOfVideos.value) { index, item ->
                Card(
                    modifier
                        .selectable(
                            selected = selectedIndex == index,
                            onClick = {
                                data.videoData.videoFile.value = item.fileUrl
                                selectedIndex = index
                            }
                        )
                        .padding(8.dp)
                        .blur(if (selectedIndex == index) 0.dp else 1.dp)
                        .border(
                            width = if (selectedIndex == index) 2.dp else 0.dp,
                            color = if (selectedIndex == index) PurpleButton else Color.Transparent,
                            shape = if (selectedIndex == index) RoundedCornerShape(10.dp) else RoundedCornerShape(
                                0.dp))
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    elevation = 5.dp
                ) {
                    ImageLoader(item.smallPosterUrl)
                }
            }
        }
    }
}
