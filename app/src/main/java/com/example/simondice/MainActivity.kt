package com.example.simondice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    var ronda = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Variables
        var record = 0
        var velocidad = 0
        var almacenamiento = arrayListOf<String>()
        var almacenamientoJugador = arrayListOf<String>()

        //Creamos las variables de los botones de la ui
        val start:Button = findViewById(R.id.start)
        val rojo:Button = findViewById(R.id.rojo)
        val amarillo:Button = findViewById(R.id.amarillo)
        val verde:Button = findViewById(R.id.verde)
        val azul:Button = findViewById(R.id.azul)

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

        fun iniciaPartida() {
            almacenamiento.clear()
            almacenamientoJugador.clear()

            rojo.isEnabled = false
            amarillo.isEnabled = false
            azul.isEnabled = false
            verde.isEnabled = false

            Toast.makeText(this, "Memoriza la secuencia", Toast.LENGTH_LONG).show()
            bBlanco()

        }

        suspend fun colorRojo() {
            rojo.setBackgroundColor(resources.getColor(R.color.rojo))
            delay(500L)
            rojo.setBackgroundColor(resources.getColor(R.color.white))
        }

        suspend fun colorAzul() {
            azul.setBackgroundColor(resources.getColor(R.color.azul))
            delay(500L)
            azul.setBackgroundColor(resources.getColor(R.color.white))
        }

        suspend fun colorVerde() {
            verde.setBackgroundColor(resources.getColor(R.color.verde))
            delay(500L)
            verde.setBackgroundColor(resources.getColor(R.color.white))
        }

        suspend fun colorAmarillo() {
            amarillo.setBackgroundColor(resources.getColor(R.color.amarillo))
            delay(500L)
            amarillo.setBackgroundColor(resources.getColor(R.color.white))
        }

        suspend fun elegirColor() {
            for (i in 1..3) {
                delay(1000L)
                val numRandom = java.util.Random().nextInt(4) + 1
                val secuenciaColores = when (numRandom) {
                    1 -> {
                        GlobalScope.launch(Dispatchers.Main) {
                            colorRojo();
                            almacenamiento.add("rojo")
                        }

                    }
                    2 -> {
                        GlobalScope.launch(Dispatchers.Main) {
                            colorAzul();
                            almacenamiento.add("azul")
                        }

                    }
                    3 -> {
                        GlobalScope.launch(Dispatchers.Main) {
                            colorAmarillo();
                            almacenamiento.add("amarillo")
                        }

                    }
                    else -> {
                        GlobalScope.launch(Dispatchers.Main) {
                            colorVerde();
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

        fun comprobarSecuencia(){

            rojo.isEnabled = false
            amarillo.isEnabled = false
            azul.isEnabled = false
            verde.isEnabled = false

            if(almacenamientoJugador == almacenamiento){
                Toast.makeText(this, "ACERTASTE", Toast.LENGTH_LONG).show()
                ronda++;
                iniciaPartida()

            } else {
                Toast.makeText(this, "FALLASTE", Toast.LENGTH_LONG).show()
            }
        }

        fun jugador() {
            var contador = 0

            //Almacenamos la secuencia que ejecute el jugador
                rojo.setOnClickListener {
                    GlobalScope.launch(Dispatchers.Main) {
                        colorRojo();
                        almacenamientoJugador.add("rojo")
                        contador++
                        Log.d("jugador", contador.toString())
                    }
                }

                amarillo.setOnClickListener {
                    GlobalScope.launch(Dispatchers.Main) {
                        colorAmarillo();
                        almacenamientoJugador.add("amarillo")
                        contador++
                        Log.d("jugador", contador.toString())
                        if(contador==3){
                            comprobarSecuencia()
                        }
                    }
                }
                verde.setOnClickListener {
                    GlobalScope.launch(Dispatchers.Main) {
                        colorVerde();
                        almacenamientoJugador.add("verde")
                        contador++
                        Log.d("jugador", contador.toString())
                        if(contador==3){
                            comprobarSecuencia()
                        }
                    }
                }
                azul.setOnClickListener {
                    GlobalScope.launch(Dispatchers.Main) {
                        colorAzul();
                        almacenamientoJugador.add("azul")
                        contador++
                        Log.d("jugador", contador.toString())
                        if(contador==3){
                            comprobarSecuencia()
                        }
                    }
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