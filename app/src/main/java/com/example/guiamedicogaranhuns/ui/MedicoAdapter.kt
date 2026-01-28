package com.example.guiamedicogaranhuns.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guiamedicogaranhuns.R
import com.example.guiamedicogaranhuns.model.Medico

class MedicoAdapter(
    private val listaMedicos: MutableList<Medico>,
    private val onExcluirClick: (Medico) -> Unit,
    private val onItemClick: (Medico) -> Unit   // 🔹 NOVO (UPDATE)
) : RecyclerView.Adapter<MedicoAdapter.MedicoViewHolder>() {

    class MedicoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNome: TextView = view.findViewById(R.id.txtNome)
        val txtEspecialidade: TextView = view.findViewById(R.id.txtEspecialidade)
        val btnExcluir: ImageButton = view.findViewById(R.id.btnExcluir)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_medico, parent, false)
        return MedicoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicoViewHolder, position: Int) {

        val posicaoAtual = holder.adapterPosition
        if (posicaoAtual == RecyclerView.NO_POSITION) return

        val medico = listaMedicos[posicaoAtual]

        holder.txtNome.text = medico.nome
        holder.txtEspecialidade.text = medico.especialidade

        // deletar
        holder.btnExcluir.setOnClickListener {
            onExcluirClick(medico)
        }

        // atualizar
        holder.itemView.setOnClickListener {
            onItemClick(medico)
        }
    }

    override fun getItemCount(): Int = listaMedicos.size

    fun removerMedico(posicao: Int) {
        if (posicao in listaMedicos.indices) {
            listaMedicos.removeAt(posicao)
            notifyItemRemoved(posicao)
        }
    }
}




