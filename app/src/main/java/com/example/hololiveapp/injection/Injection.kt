package com.example.hololiveapp.injection

import com.example.hololiveapp.data.HoloRepo

object Injection {
    fun provideRepository(): HoloRepo {
        return HoloRepo.getInstance()
    }
}