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
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Turn off the decor fitting system windows,
        // which means we need to through handling insets
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MyTheme {
                ProvideWindowInsets {
                    MyApp()
                }
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    val navController = rememberNavController()

    Surface(color = MaterialTheme.colors.background) {
        NavHost(navController, startDestination = Route.Welcome) {
            composable(Route.Welcome) {
                WelcomeScreen(onLoginClick = {
                    navController.navigate(Route.Login)
                })
            }
            composable(Route.Login) { LoginScreen() }
            composable(Route.Home) { HomeScreen() }
        }
    }
}

@Composable
fun HomeScreen() {
}

@Composable
fun LoginScreen() {
}

@Composable
fun WelcomeScreen(
    onLoginClick: () -> Unit = {},
) {
    val dark = isSystemInDarkTheme()

    val welcomeBg = if (dark) R.drawable.ic_dark_welcome_bg else R.drawable.ic_light_welcome_bg
    val illos = if (dark) R.drawable.ic_dark_welcome_illos else R.drawable.ic_light_welcome_illos
    val logo = if (dark) R.drawable.ic_dark_logo else R.drawable.ic_light_logo

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = welcomeBg),
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .padding(top = 72.dp)
        ) {
            Image(
                painter = painterResource(id = illos),
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = 48.dp)
                    .offset(x = 88.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = logo),
                    contentDescription = stringResource(id = R.string.logo)
                )
                Text(
                    style = MaterialTheme.typography.subtitle1
                        .copy(color = MaterialTheme.colors.onPrimary),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(bottom = 40.dp)
                        .baselineHeight(32.dp)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.welcome_subtitle),
                )
                Button(
                    colors = buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                    shape = MaterialTheme.shapes.medium,
                    onClick = { },
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .height(48.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.create_account))
                }
                TextButton(
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.onPrimary),
                    onClick = onLoginClick,
                    modifier = Modifier.height(48.dp)
                ) {
                    Text(text = stringResource(id = R.string.login))
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        ProvideWindowInsets {
            WelcomeScreen()
        }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        ProvideWindowInsets {
            WelcomeScreen()
        }
    }
}
