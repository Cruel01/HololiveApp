package com.example.hololiveapp.model

data class Member(
    val id: Long,
    val name: String,
    val gen: String,
    val desc: String,
    val price: Int,
    val photoUrl: String
)