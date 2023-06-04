package com.cupcake.jobsfinder.data.remote.response

import com.google.gson.annotations.SerializedName

@Serializable
data class JobTitleDto(
   val id: String,
   val title : String
)
