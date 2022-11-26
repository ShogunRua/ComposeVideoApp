package com.shogunrua.videoappvicuesoft.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shogunrua.videoappvicuesoft.presentation.screens.VideoPlayerScreen
import com.shogunrua.videoappvicuesoft.presentation.theme.PurpleButton
import com.shogunrua.videoappvicuesoft.presentation.viewmodel.VideoFilesViewModel
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

    @Composable
    fun VideoFilesScreen(
        viewModel: VideoFilesViewModel = viewModel(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column {
                Text(
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 22.dp),
                    text = "R.string.faq",
                    color = Color.Black,
                    fontSize = 22.sp,
                )

            }
        }
    }
}
