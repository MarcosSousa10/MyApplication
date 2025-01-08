package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.model.Contato

@Dao
interface ContatoDao {

    // Alterado para retornar Unit (sem valor) para o método de inserção
    @Insert
    suspend fun inserir(contato: Contato)

    // Alterado para retnar uma lista de Contatos
    @Query("SELECT * FROM contato")
    suspend fun listarTodos(): List<Contato>
}
