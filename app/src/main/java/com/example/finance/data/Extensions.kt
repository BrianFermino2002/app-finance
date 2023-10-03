package com.example.finance.data

import android.content.Context
import androidx.room.Room
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val Context.db: AppDataBase
    get() = Room.databaseBuilder(
        applicationContext,
        AppDataBase::class.java, "database-user"
    ).build()

fun String.toLocalDateTime(): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    return LocalDateTime.parse(this, formatter)
}