package com.shogunrua.videoappvicuesoft.presentation.screens

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
import com.shogunrua.videoappvicuesoft.presentation.model.ListOfVideosData
import com.shogunrua.videoappvicuesoft.presentation.model.VideoData
import com.shogunrua.videoappvicuesoft.presentation.utils.ExoPlayerInit
import com.shogunrua.videoappvicuesoft.presentation.utils.androidViewModifier
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

        if(data.textIsVisible.value)
        TextFieldTouchBox(modifier = androidViewModifier, imageModifier = imageModifier)

    }
}
