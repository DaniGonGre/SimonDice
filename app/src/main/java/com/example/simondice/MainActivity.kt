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
        var almacenamientoJugador = arrayListOf<String>()

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

        //Creamos una función que ponga los botones en blanco
        fun bBlanco() {
            rojo.setBackgroundColor(resources.getColor(R.color.white))
            verde.setBackgroundColor(resources.getColor(R.color.white))
            amarillo.setBackgroundColor(resources.getColor(R.color.white))
            azul.setBackgroundColor(resources.getColor(R.color.white))
        }

        //Creamos una función que le de color a los botones
        fun bColor() {
            rojo.setBackgroundColor(resources.getColor(R.color.rojo))
            verde.setBackgroundColor(resources.getColor(R.color.verde))
            amarillo.setBackgroundColor(resources.getColor(R.color.amarillo))
            azul.setBackgroundColor(resources.getColor(R.color.azul))
        }

        fun iniciaPartida() {
            Toast.makeText(this, "Memoriza la secuencia", Toast.LENGTH_LONG).show()
            bBlanco()

        }

        suspend fun elegirColor() {
            for (i in 1..3) {
                delay(1000L)
                val numRandom = java.util.Random().nextInt(4) + 1
                val secuenciaColores = when (numRandom) {
                    1 -> {
                        val op1 = GlobalScope.launch(Dispatchers.Main) {
                            rojo.setBackgroundColor(resources.getColor(R.color.rojo))
                            delay(500L)
                            rojo.setBackgroundColor(resources.getColor(R.color.white))
                            almacenamiento.add("rojo")
                        }

                    }
                    2 -> {
                        val op2 = GlobalScope.launch(Dispatchers.Main) {
                            azul.setBackgroundColor(resources.getColor(R.color.azul))
                            delay(500L)
                            azul.setBackgroundColor(resources.getColor(R.color.white))
                            almacenamiento.add("azul")
                        }

                    }
                    3 -> {
                        val op3 = GlobalScope.launch(Dispatchers.Main) {
                            amarillo.setBackgroundColor(resources.getColor(R.color.amarillo))
                            delay(500L)
                            amarillo.setBackgroundColor(resources.getColor(R.color.white))
                            almacenamiento.add("amarillo")
                        }

                    }
                    else -> {
                        val op4 = GlobalScope.launch(Dispatchers.Main) {
                            verde.setBackgroundColor(resources.getColor(R.color.verde))
                            delay(500L)
                            verde.setBackgroundColor(resources.getColor(R.color.white))
                            almacenamiento.add("verde")
                        }

                    }
                }
            }
            delay(500L)
            bBlanco()

            Toast.makeText(this, "Introduce la secuencia anterior", Toast.LENGTH_LONG).show()
            rojo.isEnabled = true
            amarillo.isEnabled = true
            azul.isEnabled = true
            verde.isEnabled = true
        }

        fun visualizarSecuencia() {
            val job = GlobalScope.launch(Dispatchers.Main) {
                elegirColor()
            }
        }

        fun jugador() {
            val rojo: Button = findViewById(R.id.rojo)
            val amarillo: Button = findViewById(R.id.amarillo)
            val verde: Button = findViewById(R.id.verde)
            val azul: Button = findViewById(R.id.azul)

            //Almacenamos la secuencia que ejecute el jugador
            rojo.setOnClickListener { almacenamientoJugador.add("rojo") }
            amarillo.setOnClickListener { almacenamientoJugador.add("amarillo") }
            verde.setOnClickListener { almacenamientoJugador.add("verde") }
            azul.setOnClickListener { almacenamientoJugador.add("azul") }

            //Bloqueamos botones
            rojo.isEnabled = false;
            amarillo.isEnabled = false;
            verde.isEnabled = false;
            azul.isEnabled = false;

        }

        fun comprobarSecuencia(){
            if(almacenamientoJugador == almacenamiento){
                Toast.makeText(this, "ACERTASTE", Toast.LENGTH_LONG).show()
                ronda++;
            } else {
                Toast.makeText(this, "FALLASTE", Toast.LENGTH_LONG).show()
            }
        }



        start.setOnClickListener {
            iniciaPartida()
            visualizarSecuencia()
            jugador()
            comprobarSecuencia()

            start.visibility = View.INVISIBLE
        }










    }
}