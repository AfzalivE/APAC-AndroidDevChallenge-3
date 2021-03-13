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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
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
fun MyApp(isDark: Boolean = isSystemInDarkTheme()) {
    val navController = rememberNavController()

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(navController, startDestination = Route.Welcome) {
            composable(Route.Welcome) {
                WelcomeScreen(
                    isDark = isDark,
                    onLoginClick = {
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
    var emailTextValue by remember { mutableStateOf(TextFieldValue()) }
    var passwordTextValue by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .baselineHeight(184.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.login_with_email)
        )

        LoginTextField(
            value = emailTextValue,
            onValueChange = { emailTextValue = it },
            text = stringResource(id = R.string.email_address),
            modifier = Modifier.fillMaxWidth()
        )

        LoginTextField(
            value = passwordTextValue,
            onValueChange = { passwordTextValue = it },
            text = stringResource(id = R.string.password),
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = buildAnnotatedString {
                append(stringResource(id = R.string.terms))
                addStyle(SpanStyle(textDecoration = TextDecoration.Underline), 36, 48)
                addStyle(SpanStyle(textDecoration = TextDecoration.Underline), 68, 82)
            },
            modifier = Modifier
                .fillMaxWidth()
                .baselineHeight(24.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2
        )

        Button(
            colors = buttonColors(backgroundColor = MaterialTheme.colors.secondary),
            shape = MaterialTheme.shapes.medium,
            onClick = { },
            modifier = Modifier
                .padding(bottom = 8.dp, top = 16.dp)
                .height(48.dp)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.login))
        }
    }
}

@Composable
private fun LoginTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.height(56.dp),
        label = {
            Text(
                style = MaterialTheme.typography.body1,
                text = text
            )
        }
    )
}

@Composable
fun WelcomeScreen(
    isDark: Boolean = isSystemInDarkTheme(),
    onLoginClick: () -> Unit = {},
) {
    val welcomeBg = if (isDark) R.drawable.ic_dark_welcome_bg else R.drawable.ic_light_welcome_bg
    val illos = if (isDark) R.drawable.ic_dark_welcome_illos else R.drawable.ic_light_welcome_illos
    val logo = if (isDark) R.drawable.ic_dark_logo else R.drawable.ic_light_logo

    Box(
        modifier = Modifier.background(MaterialTheme.colors.primary)
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
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {
            ProvideWindowInsets {
                // WelcomeScreen()
                LoginScreen()
            }
        }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {
            ProvideWindowInsets {
                // WelcomeScreen(isDark = true)
                LoginScreen()
            }
        }
    }
}
