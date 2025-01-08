package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.model.Contato
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var recyclerView: RecyclerView
    private val adapter = ContatoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val btnSalvar = findViewById<Button>(R.id.btnSalvar)
        val edtNome = findViewById<EditText>(R.id.edtNome)
        val edtTelefone = findViewById<EditText>(R.id.edtTelefone)

        btnSalvar.setOnClickListener {
            val nome = edtNome.text.toString()
            val telefone = edtTelefone.text.toString()
            if (nome.isNotEmpty() && telefone.isNotEmpty()) {
                salvarContato(nome, telefone)
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        carregarContatos()
    }

    private fun salvarContato(nome: String, telefone: String) {
        val contato = Contato(nome = nome, telefone = telefone)
        lifecycleScope.launch {
            database.contatoDao().inserir(contato)
            carregarContatos()
        }
    }

    private fun carregarContatos() {
        lifecycleScope.launch {
            val contatos = withContext(Dispatchers.IO) {
                database?.contatoDao()?.listarTodos()
            }
            adapter.submitList(contatos)
        }
    }
}
