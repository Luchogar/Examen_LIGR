package com.example.examen_ligr

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.examen_ligr.Respuesta.IntercambioRespuesta
import com.example.examen_ligr.Solicitud.SolicitudAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val retrofit = Retrofit.Builder().baseUrl("http://157.245.227.216:3000/api/obtieneCoordenadasXUsuario")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    private val postsApi = retrofit.create(SolicitudAPI::class.java)
    private val response = postsApi.getAllPosts()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val boton = findViewById<Button>(R.id.bInicio)as Button
        var latitud1:Double?=null
        var longitud1:Double?=null

        response.enqueue(object : Callback<IntercambioRespuesta> {
            override fun onFailure(call: Call<IntercambioRespuesta>, t: Throwable) {

            }

            override fun onResponse(call: Call<IntercambioRespuesta>, response: Response<IntercambioRespuesta>) {
                val mResponse = response.body()
                //data.value = response!!.body()!!.articles
                latitud1 =   mResponse!!.valores!!.latitud
                longitud1 = mResponse!!.valores!!.longitud
                boton.setOnClickListener {
                    val mUrlIntent = Uri.parse("geo:0,0?q=${latitud1},${longitud1}")

                    val mMapIntent = Intent(Intent.ACTION_VIEW,mUrlIntent)
                    mMapIntent.setPackage("com.google.android.apps.maps")
                    startActivity(mMapIntent)
                }
            }

        })



    }
}
