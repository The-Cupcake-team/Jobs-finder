package com.cupcake.models

data class User(
    val id: String,
    val username: String,
    val fullName: String,
    val email: String,
    val isActive: Boolean,
    val createdAt: String,
    val token: Token
)

data class Token(
    val token: String,
    val expireTime: Long
)