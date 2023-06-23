package com.cupcake.ui.utill

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun convert(time: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    val pastDateTime = LocalDateTime.parse(time, formatter)
    val currentDateTime = LocalDateTime.now()
    val duration = Duration.between(pastDateTime, currentDateTime)

    val hours = duration.toHours()
    return "$hours hours ago"
}