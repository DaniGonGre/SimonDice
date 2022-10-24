package com.example.simondice

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Variables
        var record = 0
        var velocidad = 0
        var ronda = 0
        var almacenamiento = arrayListOf<String>()

        //Creamos las variables de los botones de la ui
        val start:Button = findViewById(R.id.start)
        val rojo:Button = findViewById(R.id.rojo)
        val amarillo:Button = findViewById(R.id.amarillo)
        val verde:Button = findViewById(R.id.verde)
        val azul:Button = findViewById(R.id.azul)

        //Añadimos un array con la secuencia de colores
        val colores = arrayOf("verde", "amarillo", "azul", "rojo")
        var colorRandom = colores.random()

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

        }

        suspend fun elegirColor() {
            for (i in 1..4) {
                delay(1000L)
                if (colorRandom == "rojo"){
                    val op1 = GlobalScope.launch(Dispatchers.Main) {
                        rojo.setBackgroundColor(resources.getColor(R.color.rojo))
                    }
                }
                if (colorRandom == "azul"){
                    val op2 = GlobalScope.launch(Dispatchers.Main) {
                        azul.setBackgroundColor(resources.getColor(R.color.azul))
                    }
                }
                if (colorRandom == "amarillo"){
                    val op3 = GlobalScope.launch(Dispatchers.Main) {
                        amarillo.setBackgroundColor(resources.getColor(R.color.amarillo))
                    }
                }
                if (colorRandom == "verde"){
                    val op4 = GlobalScope.launch(Dispatchers.Main) {
                        verde.setBackgroundColor(resources.getColor(R.color.verde))
                    }
                }

            }
        }

        fun visualizarSecuencia() {
            val job = GlobalScope.launch(Dispatchers.Main) {
                elegirColor()
            }
        }



        start.setOnClickListener {
            iniciaPartida()
            visualizarSecuencia()
            start.visibility = View.INVISIBLE
        }










    }
}