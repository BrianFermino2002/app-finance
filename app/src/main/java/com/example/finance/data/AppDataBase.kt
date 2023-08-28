package com.example.finance.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.finance.data.dao.UserDAO
import com.example.finance.data.entity.LancamentoEntity
import com.example.finance.data.entity.PerguntaEntity
import com.example.finance.data.entity.RespostaEntity
import com.example.finance.data.entity.UserEntity

@Database(entities = [
        UserEntity::class,
        LancamentoEntity::class,
        PerguntaEntity::class,
        RespostaEntity::class
               ],
    version = 1)

abstract class AppDataBase: RoomDatabase() {
    abstract fun userDAO(): UserDAO
}