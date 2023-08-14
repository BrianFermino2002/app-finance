package com.example.finance.data

import android.content.Context
import androidx.room.Room

val Context.db: AppDataBase
    get() = Room.databaseBuilder(
        applicationContext,
        AppDataBase::class.java, "database-user"
    ).build()