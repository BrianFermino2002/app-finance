package com.example.finance.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.finance.data.dao.UserDAO
import com.example.finance.data.entity.LancamentoEntity
import com.example.finance.data.entity.UserEntity

@Database(entities = [
        UserEntity::class,
        LancamentoEntity::class
               ],
    version = 1)

abstract class AppDataBase: RoomDatabase() {
    abstract fun userDAO(): UserDAO
}