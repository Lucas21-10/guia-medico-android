package com.example.guiamedicogaranhuns.model

import java.io.Serializable

data class Medico(
    val id: Int? = null,
    val nome: String,
    val especialidade: String,
    val crm: String
) : Serializable

