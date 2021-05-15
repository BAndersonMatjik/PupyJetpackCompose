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

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.core.local.AppDatabase
import com.example.androiddevchallenge.core.local.repo.PuppyDao
import com.example.androiddevchallenge.core.model.PuppyEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


@HiltViewModel
class HomeViewModel @Inject constructor(puppyDao: PuppyDao) : ViewModel(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    var data: MutableState<List<PuppyEntity>> = mutableStateOf(listOf())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            puppyDao.getPuppys().apply {
                Log.d("HomeViewModel", "onCreate:  ${this.size} : $this")
                data.value = this
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
