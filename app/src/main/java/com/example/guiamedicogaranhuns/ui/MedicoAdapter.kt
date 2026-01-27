package com.example.guiamedicogaranhuns.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guiamedicogaranhuns.R
import com.example.guiamedicogaranhuns.model.Medico

class MedicoAdapter(
    private val medicos: List<Medico>
) : RecyclerView.Adapter<MedicoAdapter.MedicoViewHolder>() {

    class MedicoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNome: TextView = view.findViewById(R.id.txtNome)
        val txtEspecialidade: TextView = view.findViewById(R.id.txtEspecialidade)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_medico, parent, false)
        return MedicoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicoViewHolder, position: Int) {
        val medico = medicos[position]
        holder.txtNome.text = medico.nome
        holder.txtEspecialidade.text = medico.especialidade
    }

    override fun getItemCount() = medicos.size
}

