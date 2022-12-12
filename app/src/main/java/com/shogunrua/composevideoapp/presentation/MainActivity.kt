package com.shogunrua.composevideoapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shogunrua.composevideoapp.presentation.screens.VideoPlayerScreen
import com.shogunrua.composevideoapp.presentation.theme.PurpleButton
import dagger.hilt.android.AndroidEntryPoint

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
