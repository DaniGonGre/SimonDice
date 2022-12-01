package com.example.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
// para observar LiveDatas
import androidx.activity.viewModels
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {


    /**
     * Inicializamos dos variables, una para contar la ronda en la que estamos y otra para contar el
     * número de clicks en la pantalla. Igualamos contRonda a 3 porque serán las veces que tenga que
     * pulsar el jugador los colores en la primera ronda
     */

    var contRonda = 3
    var contador = 0

    // Creamos una constante privada para darle cambiarle la etiqueta al log
    private val TAG_LOG: String = "mensaje Main"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Cargamos el layout
        setContentView(R.layout.activity_main)

        /**
         * Creamos la variable start que se corresponde al botón creado en la interfaz gráfica, el
         * cual buscamos por su id. Al pulsar el botón Start el juego mostrará un mensaje, pondrá
         * invisible el botón de inicio y iniciará la partida
         */

        val start: Button = findViewById(R.id.start)

        start.setOnClickListener {

            Toast.makeText(this, "Memoriza la secuencia", Toast.LENGTH_LONG).show()
            start.visibility = View.INVISIBLE
            iniciaPartida()
            mostrarRonda()

        }

    }

    /**
     * Creamos una variable para visualizar el record, además dos variables más de tipo array dónde
     * recogeremos el código de colores creador aleatoriamente por el juego y otra que estará
     * compuesta por los colores que pulse el jugador en su turno
     */

    var record = 0
    var almacenamiento = arrayListOf<String>()
    var almacenamientoJugador = arrayListOf<String>()

    /**
     * Función mostrar ronda con el observer
     */

    fun mostrarRonda() {

        // Instanciamos el ViewModel
        val miModelo by viewModels<MyViewModel>()

        // Observamos cambios en livedata
        miModelo.ronda.observe(
            this,
            androidx.lifecycle.Observer(
                fun (_: Int) {
                    val ronda : TextView = findViewById(R.id.ronda)
                    if (miModelo.ronda.value != 0)
                        ronda.text = miModelo.ronda.value.toString()
                }
            )
        )
        miModelo.record.observe(
            this,
            androidx.lifecycle.Observer(
                fun (_: Int) {
                    val record : TextView = findViewById(R.id.record)
                    record.text = miModelo.record.value.toString()
                }
            )
        )
    }

    /**
     * Esta función limpia el almacenamiento de los arrays cada vez que pasamos una ronda o al
     * perder el juego, bloquea la pulsación de los botones, los pone en blanco y inicia el método
     * que visualiza la secuencia aleatoria
     */

    fun iniciaPartida() {

        val rojo: Button = findViewById(R.id.rojo)
        val amarillo: Button = findViewById(R.id.amarillo)
        val verde: Button = findViewById(R.id.verde)
        val azul: Button = findViewById(R.id.azul)

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

    /**
     * Creamos corrutinas de cada color que nos permiten poner delay al cambiar el color del botón,
     * de esta forma podremos diferenciar si un color sale dos veces seguidas
     */

    suspend fun colorRojo() {
        val rojo: Button = findViewById(R.id.rojo)

        rojo.setBackgroundColor(resources.getColor(R.color.rojo))
        delay(500L)
        rojo.setBackgroundColor(resources.getColor(R.color.white))
    }

    suspend fun colorAzul() {
        val azul: Button = findViewById(R.id.azul)

        azul.setBackgroundColor(resources.getColor(R.color.azul))
        delay(500L)
        azul.setBackgroundColor(resources.getColor(R.color.white))
    }

    suspend fun colorVerde() {
        val verde: Button = findViewById(R.id.verde)

        verde.setBackgroundColor(resources.getColor(R.color.verde))
        delay(500L)
        verde.setBackgroundColor(resources.getColor(R.color.white))
    }

    suspend fun colorAmarillo() {
        val amarillo: Button = findViewById(R.id.amarillo)

        amarillo.setBackgroundColor(resources.getColor(R.color.amarillo))
        delay(500L)
        amarillo.setBackgroundColor(resources.getColor(R.color.white))
    }

    /**
     * Esta función crea un bucle dependiendo de la ronda en la que estemos y crea una secuencia
     * random de colores, esta secuencia se almacena en un array para recogerla posteriormente.
     * Después habilitamos los botones para que los pueda pulsar el jugador
     */

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
        val start: Button = findViewById(R.id.start)
        val rojo: Button = findViewById(R.id.rojo)
        val amarillo: Button = findViewById(R.id.amarillo)
        val verde: Button = findViewById(R.id.verde)
        val azul: Button = findViewById(R.id.azul)

        rojo.setBackgroundColor(resources.getColor(R.color.white))
        verde.setBackgroundColor(resources.getColor(R.color.white))
        amarillo.setBackgroundColor(resources.getColor(R.color.white))
        azul.setBackgroundColor(resources.getColor(R.color.white))

        rojo.isEnabled = true
        amarillo.isEnabled = true
        azul.isEnabled = true
        verde.isEnabled = true
    }

    /**
     * Para visualizar la secuencia creamos un metodo en el que llamamos a la función suspendida
     * anterior, además llamamos la función jugador
     */

    fun visualizarSecuencia() {
        val job = GlobalScope.launch(Dispatchers.Main) {
            elegirColor()
        }

        jugador()
    }

    /**
     * Esta función permite introducir los colores a el usuario y almacena su secuencia de colores
     * para compararla en la funcion comprobarSecuencia()
     */

    fun jugador() {

        //Creamos las variables de los botones de la ui
        val start: Button = findViewById(R.id.start)
        val rojo: Button = findViewById(R.id.rojo)
        val amarillo: Button = findViewById(R.id.amarillo)
        val verde: Button = findViewById(R.id.verde)
        val azul: Button = findViewById(R.id.azul)

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


    /**
     * Por último, comprobamos la secuencia introducida por el jugador. Si es correcta añadimos
     * un mensaje que diga que acertó, incrementamos la ronda y el número de colores de la
     * secuencia. Si falla vuelve a empezar el programa
     */

    fun comprobarSecuencia() {

        //Creamos las variables de los botones de la ui
        val start: Button = findViewById(R.id.start)
        val rojo: Button = findViewById(R.id.rojo)
        val amarillo: Button = findViewById(R.id.amarillo)
        val verde: Button = findViewById(R.id.verde)
        val azul: Button = findViewById(R.id.azul)
        var ronda: TextView = findViewById(R.id.ronda)


        if (contador == contRonda) {

            val nRonda: TextView = findViewById(R.id.numRonda)
            //val nRecord: TextView = findViewById(R.id.numRecord)

            //var stringRonda = ""
            //var suma = 0
            //var stringRecord = ""

            contador = 0

            rojo.isEnabled = false
            amarillo.isEnabled = false
            azul.isEnabled = false
            verde.isEnabled = false

            if (almacenamientoJugador == almacenamiento) {
                Toast.makeText(this, "ACERTASTE", Toast.LENGTH_SHORT).show()
                contRonda++
                //suma = suma + 1
                //stringRonda = suma.toString()
                //nRonda.setText(stringRonda)
                iniciaPartida()

            } else {
                Toast.makeText(this, "FALLASTE", Toast.LENGTH_SHORT).show()
                contRonda = 3
                nRonda.setText("0")
                //stringRecord = record.toString()
                //nRecord.setText(stringRecord)
                iniciaPartida()
            }
        }

    }

}


