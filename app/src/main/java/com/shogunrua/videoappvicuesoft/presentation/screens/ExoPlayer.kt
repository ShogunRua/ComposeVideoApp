package com.shogunrua.videoappvicuesoft.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.ui.PlayerView
import com.shogunrua.videoappvicuesoft.presentation.model.ListOfVideosData
import com.shogunrua.videoappvicuesoft.presentation.model.VideoData
import com.shogunrua.videoappvicuesoft.presentation.utils.ExoPlayerInit
import kotlinx.coroutines.launch

@Composable
fun ExoVideoPlayer(
    data: VideoData,
    listOfVideosData: ListOfVideosData,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val exoPlayer = ExoPlayerInit.initEmptyPlayer(context)
    val scope = rememberCoroutineScope()

    AndroidView(
        modifier = modifier
            .padding(start = 16.dp, bottom = 20.dp, end = 16.dp)
            .clip(RoundedCornerShape(10.dp)),
        factory = {
            scope.launch {
                exoPlayer.apply {
                    listOfVideosData.listOfVideos.value.indices.forEach { index ->
                        if (index != 0) {
                            val fileUrl = listOfVideosData.listOfVideos.value[index].fileUrl
                            exoPlayer.addMediaItem(MediaItem.fromUri(fileUrl))
                        }
                    }
                    volume = 0f
                    repeatMode = Player.REPEAT_MODE_ALL
                    playWhenReady = true
                    addListener(object : Player.Listener {
                        override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                            super.onMediaItemTransition(mediaItem, reason)
                            data.videoCurrentIndex.value = exoPlayer.currentMediaItemIndex
                        }
                    })
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
            it.player?.setMediaItem(MediaItem.fromUri(data.videoFile.value))
        }
    )
}
