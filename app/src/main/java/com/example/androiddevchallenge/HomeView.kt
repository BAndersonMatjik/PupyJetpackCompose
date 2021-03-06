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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.ui.item.DogItem

@Composable
fun HomeView(navigation: NavController, viewModel: HomeViewModel = hiltNavGraphViewModel()) {
    fun navigate(puppy_id: String) {
        navigation.navigate("detailView/$puppy_id")
    }

    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Dogs List", fontSize = 25.sp)
            val data by viewModel.data.observeAsState()
            if (data?.size!!> 0) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    itemsIndexed(
                        items = data!!
                    ) { index, item ->
                        DogItem(
                            model = item,
                            navigateTo = {
                                navigate(item.id_puppy.toString())
                            }
                        )
                    }
                }
            } else {
                Text(text = "No Data Fetch From Db", modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Preview
@Composable
fun HomePreview() {
//    HomeView()
}
