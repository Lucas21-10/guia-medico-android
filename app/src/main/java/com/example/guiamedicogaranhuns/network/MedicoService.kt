package com.example.guiamedicogaranhuns.network

import com.example.guiamedicogaranhuns.model.Medico
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MedicoService {

    // CREATE
    @POST("medicos")
    fun criarMedico(@Body medico: Medico): Call<Medico>

    // READ (listar)
    @GET("medicos")
    fun listarMedicos(): Call<List<Medico>>

    // UPDATE
    @PUT("medicos/{id}")
    fun atualizarMedico(
        @Path("id") id: Int,
        @Body medico: Medico
    ): Call<Medico>

    // DELETE
    @DELETE("medicos/{id}")
    fun excluirMedico(@Path("id") id: Int): Call<Void>
}

