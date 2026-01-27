package com.example.guiamedicogaranhuns.ui

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
                    val medicos = response.body() ?: emptyList()
                    recyclerView.adapter = MedicoAdapter(medicos)
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
