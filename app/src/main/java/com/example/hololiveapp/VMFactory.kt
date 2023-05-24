package com.example.hololiveapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hololiveapp.data.HoloRepo
import com.example.hololiveapp.ui.theme.page.cart.CartVM
import com.example.hololiveapp.ui.theme.page.detail.DetailVM
import com.example.hololiveapp.ui.theme.page.home.HomeVM

class VMFactory(private val repo: HoloRepo) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeVM::class.java)) {
            return HomeVM(repo) as T
        } else if (modelClass.isAssignableFrom(DetailVM::class.java)) {
            return DetailVM(repo) as T
        } else if (modelClass.isAssignableFrom(CartVM::class.java)) {
            return CartVM(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}