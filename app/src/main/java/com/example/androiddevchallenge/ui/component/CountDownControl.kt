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

import android.os.CountDownTimer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@ExperimentalAnimationApi
@Composable
fun CountDownControl(
    modifier: Modifier,
    textFieldVisible: Boolean,
    timer: CountDownTimer,
) {
    val enabled = remember { mutableStateOf(true) }
    val animatedColor =
        animateColorAsState(targetValue = if (enabled.value) MaterialTheme.colors.primary else MaterialTheme.colors.secondary)
    Box(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AnimatedVisibility(
                visible = textFieldVisible,
                enter = expandHorizontally(),
                exit = shrinkHorizontally()
            ) {
                Button(
                    onClick = {
                        timer.start()
                    },
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp),
                    shape = CircleShape,
                    colors = buttonColors(backgroundColor = animatedColor.value)
                ) {
                    Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
private fun CountDownControlPreview() {
    MyTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column {
                CountDownControl(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    textFieldVisible = true,
                    timer = object : CountDownTimer(3000, 1_000) {
                        override fun onTick(millisUntilFinished: Long) {
                        }

                        override fun onFinish() {
                        }
                    }
                )
            }
        }
    }
}
