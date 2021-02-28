package com.example.androiddevchallenge.ui.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.DEFAULT_PLACEHOLDER_IMAGE
import com.example.androiddevchallenge.FAKE_DOG_URL
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
            .padding(16.dp)) {
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
fun DogsItemText(header:String, detail:String){
    Column(modifier = Modifier
        .width(IntrinsicSize.Max)
        .padding(start = 6.dp)) {
        Text(header, style = MaterialTheme.typography.subtitle1)
        Text(
            detail,
            style = MaterialTheme.typography.body1
        )
    }
}