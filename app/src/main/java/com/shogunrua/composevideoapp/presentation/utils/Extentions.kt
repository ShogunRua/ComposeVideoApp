package com.shogunrua.composevideoapp.presentation.utils

import androidx.compose.ui.geometry.Offset
import com.shogunrua.composevideoapp.domain.utils.PI
import kotlin.math.cos
import kotlin.math.sin

fun Offset.rotateBy(angle: Float): Offset {
    val angleInRadians = angle * PI / 180
    return Offset(
        (x * cos(angleInRadians) - y * sin(angleInRadians)).toFloat(),
        (x * sin(angleInRadians) + y * cos(angleInRadians)).toFloat()
    )
}
