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
package com.example.androiddevchallenge

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.component.CountDownClock
import com.example.androiddevchallenge.ui.component.CountDownControl
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.util.Converter

class MainActivity : AppCompatActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@ExperimentalAnimationApi
@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        var minute by remember { mutableStateOf(0L) }
        var second by remember { mutableStateOf(0L) }
        var textFieldVisible by remember { mutableStateOf(true) }

        val onCountDownChanged: (minute: Long, second: Long, textFieldVisible: Boolean) -> Unit =
            { m, s, isVisible ->
                minute = m
                second = s
                textFieldVisible = isVisible
            }

        val timer = object : CountDownTimer((minute * 60 + second) * 1000, 1_000) {
            override fun onTick(millisUntilFinished: Long) {
                val result = Converter.secondToPair(millisUntilFinished / 1_000)
                val m = result.first
                val s = result.second
                onCountDownChanged(m, s, false)
            }

            override fun onFinish() {
                onCountDownChanged(0, 0, true)
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight()
        ) {
            CountDownClock(modifier = Modifier.weight(1f), minute, second, textFieldVisible) {
                val convertedText = runCatching { it.toInt() }.getOrDefault(0)
                val m = (convertedText / 60).toLong()
                val s = convertedText - (m * 60)
                minute = if (m > 59) 59 else m
                second = if (s > 59) 59 else s
            }
            CountDownControl(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                textFieldVisible = textFieldVisible,
                timer = timer,
            )
        }
    }
}

@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@ExperimentalAnimationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
