package com.cupcake.jobsfinder.data.repository

interface Repository {
	suspend fun createPost(content: String): Boolean
}