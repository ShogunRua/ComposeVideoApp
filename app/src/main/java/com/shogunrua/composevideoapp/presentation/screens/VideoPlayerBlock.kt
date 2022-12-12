package com.shogunrua.composevideoapp.presentation.screens

import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shogunrua.composevideoapp.presentation.model.ListOfVideosData
import com.shogunrua.composevideoapp.presentation.model.VideoData
import com.shogunrua.composevideoapp.presentation.theme.PurpleButton
import com.shogunrua.composevideoapp.presentation.utils.rotateBy
import com.shogunrua.composevideoapp.R

@Composable
fun VideoPlayerBlock(
    videoData: VideoData,
    listOfVideosData: ListOfVideosData,
    modifier: Modifier = Modifier,
) {
    var zoom by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var centroid by remember { mutableStateOf(Offset.Zero) }
    var angle by remember { mutableStateOf(0f) }

    val imageModifier = modifier
        .fillMaxSize()
        .padding(start = 50.dp, top = 50.dp)
        .pointerInput(Unit) {
            detectTransformGestures(
                onGesture = { gestureCentroid, gesturePan, gestureZoom, gestureRotate ->
                    val oldScale = zoom
                    val newScale = zoom * gestureZoom
                    offset = (offset + gestureCentroid / oldScale).rotateBy(gestureRotate) -
                            (gestureCentroid / newScale + gesturePan / oldScale)
                    zoom = newScale.coerceIn(0.5f..5f)
                    angle += gestureRotate

                    centroid = gestureCentroid
                }
            )
        }
        .drawWithContent {
            drawContent()
        }
        .graphicsLayer {
            translationX = -offset.x * zoom
            translationY = -offset.y * zoom
            scaleX = zoom
            scaleY = zoom
            rotationZ = angle
            TransformOrigin(0f, 0f).also { transformOrigin = it }
        }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExoPlayerScreen(
            data = videoData,
            listOfVideosData = listOfVideosData,
            imageModifier = imageModifier,
        )

        Spacer(modifier = modifier.padding(20.dp))

        Button(
            onClick = {
                videoData.textIsVisible.value = true
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

    }
}
