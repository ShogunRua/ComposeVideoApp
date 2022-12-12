package com.shogunrua.composevideoapp.presentation.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val androidViewModifier = Modifier
    .fillMaxWidth()
    .padding(start = 5.dp, end = 5.dp)
    .clip(RoundedCornerShape(25.dp))
    .height(220.dp)
    .background(Color.Transparent)
