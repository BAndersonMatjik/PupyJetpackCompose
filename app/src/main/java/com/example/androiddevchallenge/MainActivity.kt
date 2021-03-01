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
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.core.local.AppDatabase
import com.example.androiddevchallenge.core.model.PuppyEntity
import com.example.androiddevchallenge.ui.item.DogItem
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.utils.loadPicture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        var data = MutableLiveData<List<PuppyEntity>>()
        var data: MutableState<List<PuppyEntity>> = mutableStateOf(listOf())
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(data)
            }
        }
        GlobalScope.launch(Dispatchers.IO) {
            AppDatabase.getInstance(applicationContext).puppyDao.getPuppys().apply {
                Log.d("MainActivity", "onCreate:  ${this.size} : ${this}")
                data.value = this
            }
        }

    }
}

// Start building your app here!
@Composable
fun MyApp(list: State<List<PuppyEntity>>) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Ready... Set... GO!")

            val data: List<PuppyEntity> = list.value
            if(data.size>0){
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    itemsIndexed(
                        items = data
                    ){index, item ->
                        DogItem(model = item, navigateTo = {

                        })
                    }
                }
            }else{
                Text(text = "No Data Fetch From Db", modifier = Modifier.fillMaxSize())
            }
            
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
//        MyApp()
    }
}

//@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
//        MyApp()
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