package com.example.guiamedicogaranhuns.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.guiamedicogaranhuns.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val edtSenha = findViewById<EditText>(R.id.edtSenha)
        val btLogin = findViewById<Button>(R.id.btLogin)

        btLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val senha = edtSenha.text.toString()
            if (email.isEmpty()) {
                Toast.makeText(this, "Preencha o campo de email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (senha.isEmpty()) {
                Toast.makeText(this, "Preencha o campo de senha", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Login válido", Toast.LENGTH_SHORT).show()
        }

    }
}