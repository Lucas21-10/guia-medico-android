package com.example.guiamedicogaranhuns.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guiamedicogaranhuns.R
import com.example.guiamedicogaranhuns.model.Medico

class MedicoAdapter(
    private val listaMedicos: MutableList<Medico>,
    private val onExcluirClick: (Medico) -> Unit,
    private val onAtualizarClick: (Medico) -> Unit
) : RecyclerView.Adapter<MedicoAdapter.MedicoViewHolder>() {

    class MedicoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNome: TextView = view.findViewById(R.id.txtNome)
        val txtEspecialidade: TextView = view.findViewById(R.id.txtEspecialidade)
        val btnExcluir: ImageButton = view.findViewById(R.id.btnExcluir)
        val btnAtualizar: Button = view.findViewById(R.id.btnAtualizar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_medico, parent, false)
        return MedicoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicoViewHolder, position: Int) {

        val medico = listaMedicos[position]

        holder.txtNome.text = medico.nome
        holder.txtEspecialidade.text = medico.especialidade

        holder.btnExcluir.setOnClickListener {
            onExcluirClick(medico)
        }

        holder.btnAtualizar.setOnClickListener {
            onAtualizarClick(medico)
        }
    }

    override fun getItemCount(): Int = listaMedicos.size
}





