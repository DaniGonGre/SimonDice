package com.example.simondice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Variables
        var random = (0..3).random()
        var record = 0
        var velocidad = 0
        var ronda = 0
        var secuencia = arrayListOf<String>()
        secuencia.add("verde")
        secuencia.add("azul")
        secuencia.add("amarillo")
        secuencia.add("rojo")

        val colores = arrayOf("verde", "amarillo", "azul", "rojo")

        fun iniciaPartida() {
            Toast.makeText(this, "Memoriza la secuencia", Toast.LENGTH_LONG).show()
        }

        fun mostrarRonda() {
            val ronda: TextView = findViewById(R.id.ronda)
            ronda.text = ("Rondas: " + ronda)
        }

        fun bRojo(rojo: Button) {
            rojo.setBackgroundColor(resources.getColor(R.color.white))
            rojo.setBackgroundColor(resources.getColor(R.color.rojo))
        }
        fun bAmarillo(amarillo: Button) {
            amarillo.setBackgroundColor(resources.getColor(R.color.white))
            amarillo.setBackgroundColor(resources.getColor(R.color.amarillo))
        }
        fun bVerde(verde: Button) {
            verde.setBackgroundColor(resources.getColor(R.color.white))
            verde.setBackgroundColor(resources.getColor(R.color.verde))
        }
        fun bAzul(azul: Button) {
            azul.setBackgroundColor(resources.getColor(R.color.white))
            azul.setBackgroundColor(resources.getColor(R.color.azul))
        }











    }
}