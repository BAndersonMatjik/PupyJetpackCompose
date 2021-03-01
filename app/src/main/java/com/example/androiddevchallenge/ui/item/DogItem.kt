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
package com.example.androiddevchallenge.ui.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.DEFAULT_PLACEHOLDER_IMAGE
import com.example.androiddevchallenge.core.model.PuppyEntity
import com.example.androiddevchallenge.ui.utils.loadPicture

@Composable
fun DogItem(
    model: PuppyEntity,
    navigateTo: (Unit) -> Unit,
) {
    val CONSTANT_MAX_DECRIPTION = 30
    Row(
        Modifier
            .clickable {
                navigateTo(Unit)
            }.fillMaxSize()
            .padding(16.dp)
    ) {
        loadPicture(url = model.puppy_image, defaultImage = DEFAULT_PLACEHOLDER_IMAGE).value?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = model.puppy_name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )
        }
        DogsItemText(header = model.puppy_name, detail = model.puppy_detail.take(CONSTANT_MAX_DECRIPTION))
    }
}

@Composable
fun DogsItemText(header: String, detail: String) {
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .padding(start = 6.dp)
    ) {
        Text(header, style = MaterialTheme.typography.subtitle1)
        Text(
            detail,
            style = MaterialTheme.typography.body1
        )
    }
}
