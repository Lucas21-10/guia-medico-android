package com.example.guiamedicogaranhuns.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guiamedicogaranhuns.R
import com.example.guiamedicogaranhuns.model.Medico
import com.example.guiamedicogaranhuns.network.ApiClient
import com.example.guiamedicogaranhuns.network.MedicoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaMedicosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var medicoService: MedicoService

    private val listaMedicos = mutableListOf<Medico>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_medicos)

        recyclerView = findViewById(R.id.recyclerMedicos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        medicoService = ApiClient.retrofit.create(MedicoService::class.java)

        carregarMedicos()
    }

    private fun carregarMedicos() {
        medicoService.listarMedicos().enqueue(object : Callback<List<Medico>> {

            override fun onResponse(
                call: Call<List<Medico>>,
                response: Response<List<Medico>>
            ) {
                if (response.isSuccessful) {

                    listaMedicos.clear()
                    listaMedicos.addAll(response.body() ?: emptyList())

                    recyclerView.adapter = MedicoAdapter(
                        listaMedicos,

                        // deletar
                        onExcluirClick = { medico ->
                            val idMedico = medico.id ?: return@MedicoAdapter

                            medicoService.excluirMedico(idMedico)
                                .enqueue(object : Callback<Void> {

                                    override fun onResponse(
                                        call: Call<Void>,
                                        response: Response<Void>
                                    ) {
                                        if (response.isSuccessful) {
                                            listaMedicos.remove(medico)
                                            recyclerView.adapter?.notifyDataSetChanged()

                                            Toast.makeText(
                                                this@ListaMedicosActivity,
                                                "Médico excluído",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }

                                    override fun onFailure(call: Call<Void>, t: Throwable) {
                                        Toast.makeText(
                                            this@ListaMedicosActivity,
                                            "Erro ao excluir",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                })
                        },

                        // ✏️ UPDATE (NOVO — ainda não implementa lógica, só navega)
                        onItemClick = { medico ->
                            val intent = Intent(
                                this@ListaMedicosActivity,
                                CadastrarMedicoActivity::class.java
                            )
                            intent.putExtra("medico", medico)
                            startActivity(intent)
                        }
                    )

                } else {
                    Toast.makeText(
                        this@ListaMedicosActivity,
                        "Erro ao carregar médicos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Medico>>, t: Throwable) {
                Toast.makeText(
                    this@ListaMedicosActivity,
                    "Falha de conexão",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}


