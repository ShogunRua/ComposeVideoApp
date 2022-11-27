package com.shogunrua.videoappvicuesoft.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.shogunrua.videoappvicuesoft.presentation.screens.VideoPlayerScreen
import com.shogunrua.videoappvicuesoft.presentation.theme.PurpleButton
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.cos
import kotlin.math.sin

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White,
            ) {
                Scaffold(
                    topBar = {
                        Row {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = null,
                                tint = PurpleButton,
                                modifier = Modifier
                                    .padding(16.dp)
                            )
                        }
                    }
                ) {
                    it.calculateBottomPadding()
                }
                VideoPlayerScreen()
            }
        }
    }
}

@Composable
private fun TransformGesturesZoomPanRotateExample() {

    var zoom by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var centroid by remember { mutableStateOf(Offset.Zero) }
    var angle by remember { mutableStateOf(0f) }


    val imageModifier = Modifier
        .fillMaxSize()
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


}

val boxModifier = Modifier
    .fillMaxWidth()
    .height(250.dp)
    .clipToBounds()
    .background(Color.LightGray)

fun Offset.rotateBy(angle: Float): Offset {
    val angleInRadians = angle * PI / 180
    return Offset(
        (x * cos(angleInRadians) - y * sin(angleInRadians)).toFloat(),
        (x * sin(angleInRadians) + y * cos(angleInRadians)).toFloat()
    )
}

private const val PI = Math.PI
