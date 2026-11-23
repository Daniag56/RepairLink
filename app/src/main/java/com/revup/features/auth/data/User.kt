package com.revup.features.auth.data

data class User(
    val email: String,
    val name: String,
    val token: String,
)