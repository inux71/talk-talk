package com.grabieckacper.talktalk.extensions

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun Calendar.messageDate(): String {
    val now: Calendar = Calendar.getInstance()

    return when {
        now.get(Calendar.YEAR) == this.get(Calendar.YEAR) &&
                now.get(Calendar.DAY_OF_YEAR) == this.get(Calendar.DAY_OF_YEAR) -> {
            SimpleDateFormat("HH:mm", Locale.getDefault()).format(this.time)
        }

        now.get(Calendar.WEEK_OF_YEAR) == this.get(Calendar.WEEK_OF_YEAR) -> {
            SimpleDateFormat("EEEE", Locale.getDefault()).format(this.time)
        }

        else -> {
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(this.time)
        }
    }
}
