package com.example.simondice

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import kotlin.random.Random

/**
 * Inicializa y modifica los datos de la app
 */
class MyViewModel(application: Application) : AndroidViewModel(application) {

    // Creamos una constante privada para darle cambiarle la etiqueta al log
    private val TAG_LOG: String = "mensaje ViewModel"

    // Este va a ser nuestra lista para la ronda, usamos mutable para modificar
    val ronda = mutableListOf<Int>()
    // definimos una MutableLiveData
    // para poder observar los valores de la MutableList<Int>
    val livedata_numbers = MutableLiveData<MutableList<Int>>()

    // Inicializamos variables cuando instanciamos
    init {
        Log.d(TAG_LOG, "Inicializamos livedata")
        livedata_numbers.value = ronda
    }

    /**
     * Función que muestra la ronda
     */
    fun enseñaRonda(): Int {
        // Si el valor de la ronda está vacío añadimos valor a la ronda
        if(ronda.isEmpty()){
            ronda.add(0)
        }
        // Añadimos al array de ronda el últmo valor
        var rondaFinal=ronda[ronda.lastIndex]
        rondaFinal++
        ronda.add(rondaFinal)
        // La mostramos en el logcat
        Log.d(TAG_LOG, "Añadimos Array al livedata:" + ronda.toString())
        // Retornamos el último valor del array ronda
        return ronda[ronda.lastIndex]
    }

    /*val db = Room.databaseBuilder(
        getApplication<Application>().applicationContext,
        AppDatabase::class.java, "datosBD"
    ).build()*/

    val db = Room.databaseBuilder(
        getApplication<Application>().applicationContext,
        AppDatabase::class.java, "database-name"
    ).build()


}