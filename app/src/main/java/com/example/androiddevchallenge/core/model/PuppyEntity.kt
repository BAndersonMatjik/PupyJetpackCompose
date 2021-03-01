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
package com.example.androiddevchallenge.core.model

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tm_puppys")
data class PuppyEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id_puppy: Int,
    val puppy_name: String = "",
    val puppy_detail: String = "",
    @Nullable
    val puppy_tips: String? = "",
    @Nullable
    val puppy_healt_issue: String? = "",
    val puppy_life_span: String = "",
    @Nullable
    val puppy_behavior: String? = "",
    val puppy_size: String = "",
    val puppy_weight: String = "",
    val puppy_height: String = "",
    val puppy_image: String = ""
)
