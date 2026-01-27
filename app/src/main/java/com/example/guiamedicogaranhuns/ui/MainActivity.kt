package com.example.guiamedicogaranhuns.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.guiamedicogaranhuns.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnListar = findViewById<Button>(R.id.btnListarMedicos)
        val btnCadastrar = findViewById<Button>(R.id.btnCadastrarMedico)

        // Ir para LISTAGEM (READ)
        btnListar.setOnClickListener {
            val intent = Intent(this, ListaMedicosActivity::class.java)
            startActivity(intent)
        }

        btnCadastrar.setOnClickListener {
            startActivity(Intent(this, CadastrarMedicoActivity::class.java))
        }


    }
}

