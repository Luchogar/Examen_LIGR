package com.example.examen_ligr.Solicitud

import com.example.examen_ligr.Respuesta.IntercambioRespuesta
import retrofit2.Call
import retrofit2.http.GET

interface SolicitudAPI {
    @GET("post")
    fun getAllPosts(): Call<IntercambioRespuesta>
}