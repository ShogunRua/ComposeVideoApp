package com.shogunrua.videoappvicuesoft.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.ui.PlayerView
import com.shogunrua.videoappvicuesoft.presentation.model.VideoData
import com.shogunrua.videoappvicuesoft.presentation.utils.ExoPlayerUtil

@Composable
fun ExoVideoPlayer(
    data: VideoData,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val exoPlayer = ExoPlayerUtil.initEmptyPlayer(context)

    exoPlayer.apply {
        val source = ExoPlayerUtil.getMediaSource(data.videoFile.value)
        volume = 0f
        setMediaSource(source)
        prepare()
        seekTo(0, 0)
        repeatMode = Player.REPEAT_MODE_ALL
        playWhenReady = true

    }
    AndroidView(
        modifier = modifier
            .padding(start = 16.dp, bottom = 20.dp, end = 16.dp),
        factory = {
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
