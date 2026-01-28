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

class EditarMedicoActivity : AppCompatActivity() {

    private lateinit var medicoService: MedicoService
    private lateinit var medico: Medico

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_medico)

        medicoService = ApiClient.retrofit.create(MedicoService::class.java)

        val edtNome = findViewById<EditText>(R.id.edtNome)
        val edtEspecialidade = findViewById<EditText>(R.id.edtEspecialidade)
        val edtCrm = findViewById<EditText>(R.id.edtCrm)
        val btnAtualizar = findViewById<Button>(R.id.btnAtualizar)

        medico = intent.getSerializableExtra("medico") as Medico

        // Preenche os campos
        edtNome.setText(medico.nome)
        edtEspecialidade.setText(medico.especialidade)
        edtCrm.setText(medico.crm)

        btnAtualizar.setOnClickListener {

            val medicoAtualizado = Medico(
                id = medico.id,
                nome = edtNome.text.toString(),
                especialidade = edtEspecialidade.text.toString(),
                crm = edtCrm.text.toString()
            )

            medicoService.atualizarMedico(medico.id!!, medicoAtualizado)
                .enqueue(object : Callback<Medico> {

                    override fun onResponse(
                        call: Call<Medico>,
                        response: Response<Medico>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(
                                this@EditarMedicoActivity,
                                "Médico atualizado com sucesso",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<Medico>, t: Throwable) {
                        Toast.makeText(
                            this@EditarMedicoActivity,
                            "Erro ao atualizar médico",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
    }
}
