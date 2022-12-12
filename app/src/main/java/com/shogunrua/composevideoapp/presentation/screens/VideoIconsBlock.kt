package com.shogunrua.composevideoapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shogunrua.composevideoapp.presentation.model.ListOfVideosData
import com.shogunrua.composevideoapp.presentation.model.VideoData
import com.shogunrua.composevideoapp.presentation.theme.GrayBackGround
import com.shogunrua.composevideoapp.presentation.theme.PurpleButton

@Composable
fun VideoIconsBlock(
    videoData: VideoData,
    listOfVideosData: ListOfVideosData,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier
            .background(color = GrayBackGround)
            .selectableGroup(),
    ) {
        itemsIndexed(listOfVideosData.listOfVideos.value) { index, item ->
            Card(
                modifier
                    .selectable(
                        selected = videoData.videoCurrentIndex.value == index,
                        onClick = {
                            videoData.videoFile.value = item.fileUrl
                            videoData.videoCurrentIndex.value = index
                        }
                    )
                    .padding(8.dp)
                    .alpha(if (videoData.videoCurrentIndex.value == index) 1f else 0.5f)
                    .border(
                        width = if (videoData.videoCurrentIndex.value == index) 2.dp else 0.dp,
                        color = if (videoData.videoCurrentIndex.value == index) PurpleButton else Color.Transparent,
                        shape = if (videoData.videoCurrentIndex.value == index) {
                            RoundedCornerShape(10.dp)
                        } else RoundedCornerShape(0.dp))
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                elevation = 5.dp,
            ) {
                ImageLoader(item.smallPosterUrl)
            }
        }
    }
}
