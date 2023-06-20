package com.cupcake.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class JobTitleDto(
   val id: Int,
   val title : String
)
