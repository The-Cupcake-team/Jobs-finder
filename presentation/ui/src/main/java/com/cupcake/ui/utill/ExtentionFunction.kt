package com.cupcake.ui.utill

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun convert(timestamp: Long): String {
    val currentTimestamp = System.currentTimeMillis()
    val secondsAgo = (currentTimestamp - timestamp) / 1000

    val duration = Duration.ofSeconds(secondsAgo)
    val formatter = DateTimeFormatter.ofPattern("h 'hours ago'", Locale.getDefault())
    val formattedString = formatter.format(LocalDateTime.now().minus(duration))

    return formattedString
}