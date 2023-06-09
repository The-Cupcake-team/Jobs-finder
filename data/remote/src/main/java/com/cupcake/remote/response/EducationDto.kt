package com.cupcake.remote.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class EducationDto(
    @SerializedName("city")
    val city: String? ,
    @SerializedName("date")
    val date: Date? ,
    @SerializedName("degree")
    val degree: String? ,
    @SerializedName("id")
    val id: String?,
    @SerializedName("school")
    val school: String? ,
    @SerializedName("userId")
    val userId: String?
)
@Serializable
data class Date(
    @SerializedName("end")
    val end: String? ,
    @SerializedName("start")
    val start: String?
)