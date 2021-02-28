package com.example.androiddevchallenge

import android.app.Application
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.example.androiddevchallenge.core.local.AppDatabase
import com.example.androiddevchallenge.core.local.repo.PuppyDao

class App:Application(){

    override fun onCreate() {
        super.onCreate()
        AppDatabase.getInstance(this.applicationContext)
    }
}