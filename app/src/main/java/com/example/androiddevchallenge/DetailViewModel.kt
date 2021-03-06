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

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.core.local.repo.PuppyDao
import com.example.androiddevchallenge.core.model.PuppyEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val puppyDao: PuppyDao) : ViewModel() {

    var data: MutableLiveData<PuppyEntity> = MutableLiveData<PuppyEntity>()

    fun getPuppy(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            puppyDao.getPuppys().let {
                Log.d("HomeViewModel", "onCreate:  ${it.size} : $it")
                withContext(Dispatchers.Main) {
                    data.value = it.filter { it.id_puppy == id.toInt() }.first()
                }
            }
        }
    }
}
