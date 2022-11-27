package com.shogunrua.videoappvicuesoft.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TextFieldTouchBox(
    modifier: Modifier,
    imageModifier: Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = "TEXT",
            color = Color.Green,
            modifier = imageModifier,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}
