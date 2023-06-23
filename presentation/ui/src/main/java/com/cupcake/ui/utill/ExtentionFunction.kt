package com.cupcake.ui.utill

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun convert(timestamp: String): String {
    val currentTimestamp = System.currentTimeMillis()
    val secondsAgo = (currentTimestamp - stringDataToTimestamp(timestamp)) / 1000

    val duration = Duration.ofSeconds(secondsAgo)
    val formatter = DateTimeFormatter.ofPattern("h 'hours ago'", Locale.getDefault())
    val formattedString = formatter.format(LocalDateTime.now().minus(duration))

    return formattedString
}

private fun stringDataToTimestamp(data: String): Long {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    return format.parse(data).time
}