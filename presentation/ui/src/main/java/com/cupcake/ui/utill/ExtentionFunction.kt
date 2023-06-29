package com.cupcake.ui.utill

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

//@RequiresApi(Build.VERSION_CODES.O)
//fun convert(timestamp: String): String {
//    val currentTimestamp = System.currentTimeMillis()
//    val secondsAgo = (currentTimestamp - stringDataToTimestamp(timestamp)) / 1000
//
//    val duration = Duration.ofSeconds(secondsAgo)
//    val formatter = DateTimeFormatter.ofPattern("h 'hours ago'", Locale.getDefault())
//    val formattedString = formatter.format(LocalDateTime.now().minus(duration))
//
//    return formattedString
//}
//
//private fun stringDataToTimestamp(data: String): Long {
//    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
//    return format.parse(data).time
//}


@RequiresApi(Build.VERSION_CODES.O)
fun convert(timestamp: String): String {
    val currentTimestamp = Instant.now().toEpochMilli()
    val secondsAgo = (currentTimestamp - stringDataToTimestamp(timestamp)) / 1000

    val duration = Duration.ofSeconds(secondsAgo)

    return when {
        duration.toMinutes() < 1 -> "Just now"
        duration.toHours() < 1 -> "${duration.toMinutes()} minutes ago"
        duration.toDays() < 1 -> "${duration.toHours()} hours ago"
        duration.toDays() < 7 -> "${duration.toDays()} days ago"
        else -> "${duration.toDays() / 7} weeks ago"
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun stringDataToTimestamp(data: String): Long {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.getDefault())
    val dateTime = LocalDateTime.parse(data, formatter)
    return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}
