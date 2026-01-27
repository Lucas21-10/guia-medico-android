package com.example.guiamedicogaranhuns.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.guiamedicogaranhuns.R
import com.example.guiamedicogaranhuns.model.Medico
import com.example.guiamedicogaranhuns.network.ApiClient
import com.example.guiamedicogaranhuns.network.MedicoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastrarMedicoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_medico)

        val edtNome = findViewById<EditText>(R.id.edtNome)
        val edtCrm = findViewById<EditText>(R.id.edtCrm)
        val edtEspecialidade = findViewById<EditText>(R.id.edtEspecialidade)
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)

        val medicoService = ApiClient.retrofit.create(MedicoService::class.java)

        btnSalvar.setOnClickListener {
            val nome = edtNome.text.toString()
            val crm = edtCrm.text.toString()
            val especialidade = edtEspecialidade.text.toString()

            if (nome.isEmpty() || crm.isEmpty() || especialidade.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val medico = Medico(
                id = null,
                nome = nome,
                especialidade = especialidade,
                crm = crm
            )

            medicoService.criarMedico(medico).enqueue(object : Callback<Medico> {
                override fun onResponse(call: Call<Medico>, response: Response<Medico>) {
                    Toast.makeText(
                        this@CadastrarMedicoActivity,
                        "Médico cadastrado com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }

                override fun onFailure(call: Call<Medico>, t: Throwable) {
                    Toast.makeText(
                        this@CadastrarMedicoActivity,
                        "Erro ao cadastrar médico",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }

    }
}
