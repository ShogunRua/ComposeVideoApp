package com.shogunrua.composevideoapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.sp
import com.shogunrua.composevideoapp.R
import com.shogunrua.composevideoapp.domain.utils.EMPTY_STRING

@Composable
fun TextFieldTouchBox(
    modifier: Modifier,
    imageModifier: Modifier,
) {
    var text by remember {
        mutableStateOf(EMPTY_STRING)
    }

    var onFocused by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        TextField(
            modifier = imageModifier
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) onFocused = true
                },
            value = text,
            textStyle = TextStyle.Default.copy(fontSize = 22.sp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Green,
                backgroundColor = Color.Transparent,
                placeholderColor = Color.Transparent,
                focusedLabelColor = Color.Transparent,
                cursorColor = Color.Unspecified,
                focusedIndicatorColor = Color.Transparent,
                disabledPlaceholderColor = Color.Transparent,
            ),
            label = {
                if(!onFocused && text.isEmpty()) {
                    Text(
                        text = stringResource(id = R.string.button_text),
                        color = Color.Green,
                        fontStyle = FontStyle.Italic,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            },
            onValueChange = {
                text = it
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    onFocused = false
                },
            )
        )
    }
}
