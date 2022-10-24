package com.example.simondice

import android.graphics.Color
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

        //Creamos las variables de los botones de la ui
        val start:Button = findViewById(R.id.start)
        val rojo:Button = findViewById(R.id.rojo)
        val amarillo:Button = findViewById(R.id.amarillo)
        val verde:Button = findViewById(R.id.verde)
        val azul:Button = findViewById(R.id.azul)

        //Añadimos un array con la secuencia de colores
        val colores = arrayOf("verde", "amarillo", "azul", "rojo")
        var colorRandom = colores.random()

        /*var secuencia = arrayListOf<String>()
        secuencia.add("verde")
        secuencia.add("azul")
        secuencia.add("amarillo")
        secuencia.add("rojo")
        val secuenciaRandom = secuencia.random()*/


        fun visualizarColor() {

            if (colorRandom == "verde"){
                verde.setBackgroundColor(Color.GREEN)
            }

            if (colorRandom == "rojo"){
                verde.setBackgroundColor(Color.RED)
            }

            if (colorRandom == "azul"){
                verde.setBackgroundColor(Color.BLUE)
            }

            if (colorRandom == "amarillo"){
                verde.setBackgroundColor(Color.YELLOW)
            }

        }

        fun mostrarRonda() {
            val ronda: TextView = findViewById(R.id.ronda)
            ronda.text = ("Rondas: " + ronda)
        }

        fun bBlanco() {
            rojo.setBackgroundColor(resources.getColor(R.color.white))
            verde.setBackgroundColor(resources.getColor(R.color.white))
            amarillo.setBackgroundColor(resources.getColor(R.color.white))
            azul.setBackgroundColor(resources.getColor(R.color.white))
        }

        fun iniciaPartida() {
            Toast.makeText(this, "Memoriza la secuencia", Toast.LENGTH_LONG).show()
            bBlanco()
            visualizarColor()
        }

        start.setOnClickListener {
            iniciaPartida()
        }










    }
}