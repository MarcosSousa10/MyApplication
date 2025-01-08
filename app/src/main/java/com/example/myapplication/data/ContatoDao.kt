package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.model.Contato

@Dao
interface ContatoDao {

    @Insert
    suspend fun inserir(contato: Contato)

    @Query("SELECT * FROM contato")
    suspend fun listarTodos(): List<Contato>
}
