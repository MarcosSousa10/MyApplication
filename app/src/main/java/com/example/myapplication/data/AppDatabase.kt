package com.example.myapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.model.Contato

@Database(entities = [Contato::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contatoDao(): ContatoDao
}
