package com.shogunrua.composevideoapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.ui.PlayerView
import com.shogunrua.composevideoapp.domain.utils.FLOAT_0
import com.shogunrua.composevideoapp.domain.utils.LONG_0
import com.shogunrua.composevideoapp.presentation.model.ListOfVideosData
import com.shogunrua.composevideoapp.presentation.model.VideoData
import com.shogunrua.composevideoapp.presentation.utils.ExoPlayerInit
import com.shogunrua.composevideoapp.presentation.utils.androidViewModifier
import kotlinx.coroutines.launch

@Composable
fun ExoPlayerScreen(
    modifier: Modifier = Modifier,
    imageModifier: Modifier,
    data: VideoData,
    listOfVideosData: ListOfVideosData,
) {
    val context = LocalContext.current
    val exoPlayer = ExoPlayerInit.initEmptyPlayer(context)
    val scope = rememberCoroutineScope()

    Box(
        modifier = androidViewModifier
            .padding(start = 5.dp, bottom = 20.dp, end = 5.dp)
            .clip(RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            modifier = modifier,
            factory = {
                scope.launch {
                    exoPlayer.apply {
                        setMediaItems(
                            listOfVideosData.listOfMediaItems.value,
                            data.videoCurrentIndex.value,
                            LONG_0
                        )
                        addListener(object : Player.Listener {
                            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                                super.onMediaItemTransition(mediaItem, reason)
                                data.videoCurrentIndex.value = exoPlayer.currentMediaItemIndex
                            }
                        })
                        volume = FLOAT_0
                        repeatMode = Player.REPEAT_MODE_ALL
                        playWhenReady = true
                        prepare()
                        play()
                    }
                }
                PlayerView(it).apply {
                    this.useController = false
                    player = exoPlayer
                }
            },
            update = {
                it.player?.seekTo(data.videoCurrentIndex.value, LONG_0)
            }
        )

        if (data.textIsVisible.value) {
            TextFieldTouchBox(modifier = androidViewModifier, imageModifier = imageModifier)
        }
    }
}
