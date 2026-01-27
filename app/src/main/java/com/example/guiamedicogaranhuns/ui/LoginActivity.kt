package com.example.guiamedicogaranhuns.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.guiamedicogaranhuns.R
import com.example.guiamedicogaranhuns.network.ApiClient
import com.example.guiamedicogaranhuns.network.AuthService
import com.example.guiamedicogaranhuns.network.LoginRequest
import com.example.guiamedicogaranhuns.network.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    private lateinit var authService: AuthService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        authService = ApiClient.retrofit.create(AuthService::class.java)

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

            val request = LoginRequest(
                login = email,
                senha = senha
            )

            authService.login(request).enqueue(object : Callback<LoginResponse> {

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@LoginActivity,
                            response.body()?.mensagem ?: "Resposta vazia",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Erro no login",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Falha de conexão: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        }
    }
}