package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.Contato

class ContatoAdapter : ListAdapter<Contato, ContatoAdapter.ContatoViewHolder>(ContatoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contato, parent, false)
        return ContatoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContatoViewHolder, position: Int) {
        val contato = getItem(position)
        holder.bind(contato)
    }

    class ContatoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtNome: TextView = itemView.findViewById(R.id.txtNome)
        private val txtTelefone: TextView = itemView.findViewById(R.id.txtTelefone)

        fun bind(contato: Contato) {
            txtNome.text = contato.nome
            txtTelefone.text = contato.telefone
        }
    }

    class ContatoDiffCallback : DiffUtil.ItemCallback<Contato>() {
        override fun areItemsTheSame(oldItem: Contato, newItem: Contato) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Contato, newItem: Contato) = oldItem == newItem
    }
}
