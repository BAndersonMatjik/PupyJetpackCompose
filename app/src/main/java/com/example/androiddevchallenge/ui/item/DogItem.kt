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
import com.example.androiddevchallenge.ui.utils.loadPicture

@Composable
fun DogItem(
    model: String,
    navigateTo: (Unit) -> Unit,
) {
    Row(
        Modifier
            .clickable {
                navigateTo(Unit)
            }.fillMaxSize()
            .padding(16.dp)) {
        loadPicture(url = FAKE_DOG_URL, defaultImage = DEFAULT_PLACEHOLDER_IMAGE).value?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "dogs",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )
        }
        DogsItemText(header = "Dogs", detail = "Lorem Ipsum Ipsum")
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