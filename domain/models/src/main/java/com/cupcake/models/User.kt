package com.cupcake.models

data class User(
    val id: String,
    val username: String,
    val fullName: String,
    val email: String,
    val isActive: Boolean,
    val createdAt: Long,
    val token: String
)