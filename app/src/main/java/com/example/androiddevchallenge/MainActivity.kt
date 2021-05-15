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

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.core.model.PuppyEntity
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.utils.loadPicture
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val homeViewModel = viewModels<HomeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                AppNavigator()
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        AppNavigator()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        AppNavigator()
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview("Detail Theme", widthDp = 360, heightDp = 640, showBackground = true)
@Composable
fun DetailPreview() {
    Surface(color = MaterialTheme.colors.background) {
        MyTheme(darkTheme = false) {
            val data = mutableStateOf<PuppyEntity>(
                PuppyEntity(
                    1,
                    "Bulldog",
                    "happy",
                    "",
                    "",
                    "2 Years",
                    "",
                    "Big",
                    "2 pound",
                    "",
                    "www.google.com"
                )
            )
//            DetailView(viewModel = , id = )
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        navigation(startDestination = "homeView",route = "home"){
            composable("homeView") {
                HomeView(navController)
            }
            composable(
                "detailView/{id_puppy}",
                arguments = listOf(
                    navArgument("id_puppy") {
                        type = NavType.StringType
                    }
                )
            ) { backstack ->
                backstack.arguments?.getString("id_puppy").let {
                DetailView( id = it!!)
                    Log.d("Detail", "AppNavigator: GO Detail")
                }
            }
        }
    }
}

const val DEFAULT_PLACEHOLDER_IMAGE = R.drawable.img_placeholder_loading
const val FAKE_DOG_URL =
    "\"https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dog-puppy-on-garden-royalty-free-image-1586966191.jpg\""

@Preview(name = "Image View Preview", widthDp = 300, heightDp = 300)
@Composable
fun PreviewLoadPicture() {
    Box(modifier = Modifier.fillMaxSize()) {
        loadPicture(url = FAKE_DOG_URL, defaultImage = DEFAULT_PLACEHOLDER_IMAGE)
    }
}

class FakeImageUrl : PreviewParameterProvider<String> {
    override val values =
        sequenceOf("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dog-puppy-on-garden-royalty-free-image-1586966191.jpg")
    override val count: Int = values.count()
}

class FakeImageLoadingResource : PreviewParameterProvider<Int> {
    override val values = sequenceOf(R.drawable.img_placeholder_loading)
    override val count: Int = values.count()
}
