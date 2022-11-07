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

    var contRonda = 3
    var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Creamos las variables de los botones de la ui
        val start:Button = findViewById(R.id.start)

        start.setOnClickListener {

            Toast.makeText(this, "Memoriza la secuencia", Toast.LENGTH_LONG).show()
            start.visibility = View.INVISIBLE
            iniciaPartida()

        }

    }

        //Variables
        var record = 0
        var velocidad = 0
        var almacenamiento = arrayListOf<String>()
        var almacenamientoJugador = arrayListOf<String>()

        fun iniciaPartida() {

            val start:Button = findViewById(R.id.start)
            val rojo:Button = findViewById(R.id.rojo)
            val amarillo:Button = findViewById(R.id.amarillo)
            val verde:Button = findViewById(R.id.verde)
            val azul:Button = findViewById(R.id.azul)

            almacenamiento.clear()
            almacenamientoJugador.clear()

            rojo.isEnabled = false
            amarillo.isEnabled = false
            azul.isEnabled = false
            verde.isEnabled = false

            rojo.setBackgroundColor(resources.getColor(R.color.white))
            verde.setBackgroundColor(resources.getColor(R.color.white))
            amarillo.setBackgroundColor(resources.getColor(R.color.white))
            azul.setBackgroundColor(resources.getColor(R.color.white))

            visualizarSecuencia()
        }

        suspend fun colorRojo() {
            val rojo:Button = findViewById(R.id.rojo)

            rojo.setBackgroundColor(resources.getColor(R.color.rojo))
            delay(500L)
            rojo.setBackgroundColor(resources.getColor(R.color.white))
        }

        suspend fun colorAzul() {
            val azul:Button = findViewById(R.id.azul)

            azul.setBackgroundColor(resources.getColor(R.color.azul))
            delay(500L)
            azul.setBackgroundColor(resources.getColor(R.color.white))
        }

        suspend fun colorVerde() {
            val verde:Button = findViewById(R.id.verde)

            verde.setBackgroundColor(resources.getColor(R.color.verde))
            delay(500L)
            verde.setBackgroundColor(resources.getColor(R.color.white))
        }

        suspend fun colorAmarillo() {
            val amarillo:Button = findViewById(R.id.amarillo)

            amarillo.setBackgroundColor(resources.getColor(R.color.amarillo))
            delay(500L)
            amarillo.setBackgroundColor(resources.getColor(R.color.white))
        }

        suspend fun elegirColor() {

            for (i in 1..contRonda) {
                delay(1000L)
                val numRandom = java.util.Random().nextInt(4) + 1
                val secuenciaColores = when (numRandom) {
                    1 -> {
                        GlobalScope.launch(Dispatchers.Main) {
                            colorRojo()
                            almacenamiento.add("rojo")
                        }

                    }
                    2 -> {
                        GlobalScope.launch(Dispatchers.Main) {
                            colorAzul()
                            almacenamiento.add("azul")
                        }

                    }
                    3 -> {
                        GlobalScope.launch(Dispatchers.Main) {
                            colorAmarillo()
                            almacenamiento.add("amarillo")
                        }

                    }
                    else -> {
                        GlobalScope.launch(Dispatchers.Main) {
                            colorVerde()
                            almacenamiento.add("verde")
                        }

                    }
                }
            }
            delay(500L)

            //Creamos las variables de los botones de la ui
            val start:Button = findViewById(R.id.start)
            val rojo:Button = findViewById(R.id.rojo)
            val amarillo:Button = findViewById(R.id.amarillo)
            val verde:Button = findViewById(R.id.verde)
            val azul:Button = findViewById(R.id.azul)

            rojo.setBackgroundColor(resources.getColor(R.color.white))
            verde.setBackgroundColor(resources.getColor(R.color.white))
            amarillo.setBackgroundColor(resources.getColor(R.color.white))
            azul.setBackgroundColor(resources.getColor(R.color.white))

            rojo.isEnabled = true
            amarillo.isEnabled = true
            azul.isEnabled = true
            verde.isEnabled = true
        }

        fun visualizarSecuencia() {
            val job = GlobalScope.launch(Dispatchers.Main) {
                elegirColor()
            }

            jugador()
        }

        fun comprobarSecuencia() {

            //Creamos las variables de los botones de la ui
            val start:Button = findViewById(R.id.start)
            val rojo:Button = findViewById(R.id.rojo)
            val amarillo:Button = findViewById(R.id.amarillo)
            val verde:Button = findViewById(R.id.verde)
            val azul:Button = findViewById(R.id.azul)
            var ronda: TextView = findViewById(R.id.ronda)

            if(contador==contRonda) {

                val nRonda: TextView = findViewById(R.id.numRonda)
                var nString = ""
                var suma = 0

                contador = 0

                rojo.isEnabled = false
                amarillo.isEnabled = false
                azul.isEnabled = false
                verde.isEnabled = false

                if (almacenamientoJugador == almacenamiento) {
                    Toast.makeText(this, "ACERTASTE", Toast.LENGTH_SHORT).show()
                    contRonda++
                    suma = suma + 1
                    nString = suma.toString()
                    nRonda.setText(nString)
                    iniciaPartida()

                } else {
                    Toast.makeText(this, "FALLASTE", Toast.LENGTH_SHORT).show()
                    contRonda=3
                    nRonda.setText("0")
                    iniciaPartida()


                }
            }

        }

        fun jugador() {

            //Creamos las variables de los botones de la ui
            val start:Button = findViewById(R.id.start)
            val rojo:Button = findViewById(R.id.rojo)
            val amarillo:Button = findViewById(R.id.amarillo)
            val verde:Button = findViewById(R.id.verde)
            val azul:Button = findViewById(R.id.azul)

            rojo.setBackgroundColor(resources.getColor(R.color.white))
            verde.setBackgroundColor(resources.getColor(R.color.white))
            amarillo.setBackgroundColor(resources.getColor(R.color.white))
            azul.setBackgroundColor(resources.getColor(R.color.white))

            //Almacenamos la secuencia que ejecute el jugador
            rojo.setOnClickListener {
                GlobalScope.launch(Dispatchers.Main) {
                    colorRojo()
                    almacenamientoJugador.add("rojo")
                    contador++
                    comprobarSecuencia()
                }
            }

            amarillo.setOnClickListener {
                GlobalScope.launch(Dispatchers.Main) {
                    colorAmarillo()
                    almacenamientoJugador.add("amarillo")
                    contador++
                    comprobarSecuencia()
                }
            }
            verde.setOnClickListener {
                GlobalScope.launch(Dispatchers.Main) {
                    colorVerde()
                    almacenamientoJugador.add("verde")
                    contador++
                    comprobarSecuencia()
                }
            }
            azul.setOnClickListener {
                GlobalScope.launch(Dispatchers.Main) {
                    colorAzul()
                    almacenamientoJugador.add("azul")
                    contador++
                    comprobarSecuencia()
                }
            }
        }

    }


