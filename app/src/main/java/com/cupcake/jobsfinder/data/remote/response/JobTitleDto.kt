package com.cupcake.jobsfinder.data.remote.response

import com.google.gson.annotations.SerializedName

data class JobTitleDto(
   @SerializedName("id") val id: String,
   @SerializedName("title") val title : String
)
