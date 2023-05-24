package com.example.hololiveapp.ui.theme.page.cart

import com.example.hololiveapp.model.Merch

data class CartState(
    val merch: List<Merch>,
    val totalPrice: Int
)