/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.component

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.util.Converter

@ExperimentalAnimationApi
@Composable
fun CountDownClock(
    modifier: Modifier,
    minute: Long,
    second: Long,
    textFieldVisible: Boolean,
    onValueChanged: (String) -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(style = MaterialTheme.typography.h1, text = "$minute:$second")
    }
    val value = (minute * 60 + second).toString()
    Log.d("Outline", value)
    Row {
        AnimatedVisibility(
            visible = textFieldVisible,
            enter = expandHorizontally(),
            exit = shrinkHorizontally()
        ) {
            OutlinedTextField(
                value = Converter.pairToSecond(minute to second).toString(),
                onValueChange = onValueChanged,
                label = { Text("Second") }
            )
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
private fun CountDownClockPreview() {
    MyTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column {
                CountDownClock(
                    modifier = Modifier
                        .weight(1f),
                    0,
                    0,
                    true
                ) {}
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
private fun CountDownClockDarkPreview() {
    MyTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colors.background) {
            Column {
                CountDownClock(
                    modifier = Modifier
                        .weight(1f),
                    0,
                    0,
                    true
                ) {}
            }
        }
    }
}
