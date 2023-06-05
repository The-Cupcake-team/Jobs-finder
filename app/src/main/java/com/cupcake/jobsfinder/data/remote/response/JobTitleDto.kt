package com.cupcake.jobsfinder.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class JobTitleDto(
   val id: String,
   val title : String
)
