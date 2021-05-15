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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import com.example.androiddevchallenge.ui.utils.loadPicture

@Composable
fun DetailView(id: String, viewModel: DetailViewModel = hiltNavGraphViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val data by viewModel.data.observeAsState()
        viewModel.getPuppy(id)
        data?.puppy_image?.let { image ->
            loadPicture(
                url = image,
                defaultImage = DEFAULT_PLACEHOLDER_IMAGE
            ).value?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Dogs",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
            }
            Text(
                text = data?.puppy_name!!,
                fontSize = 20.sp,
                fontStyle = FontStyle.Normal,
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Text(text = "Detail ")
            Text(text = data?.puppy_detail!!)
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "Dogs Size       ${data?.puppy_size}", style = MaterialTheme.typography.body1)
            Text(
                text = "Dogs Life Span  ${data?.puppy_life_span}",
                style = MaterialTheme.typography.body1
            )
            Text(text = "Dogs Height     ${data?.puppy_height}", style = MaterialTheme.typography.body1)
            Text(text = "Dogs Weight     ${data?.puppy_weight}", style = MaterialTheme.typography.body1)
        }
    }
}
