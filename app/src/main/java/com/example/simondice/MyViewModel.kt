package com.example.simondice

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

/**
 * Inicializa y modifica los datos de la app
 */
class MyViewModel() : ViewModel() {

    // Creamos una constante privada para darle cambiarle la etiqueta al log
    private val TAG_LOG: String = "mensaje ViewModel"

    // Este va a ser nuestra lista para la secuencia random, usamos mutable para modificar
    val numbers = mutableListOf<Int>()
    // definimos una MutableLiveData
    // para poder observar los valores de la MutableList<Int>
    val livedata_numbers = MutableLiveData<MutableList<Int>>()

    // Inicializamos variables cuando instanciamos
    init {
        Log.d(TAG_LOG, "Inicializamos livedata")
        livedata_numbers.value = numbers
    }

    /**
     * Función que añade un valor random a la lista
     */
    fun sumarRandom() {
        // Añadimos entero random a la lista
        numbers.add(Random.nextInt(0,4))
        // Actualizamos el livedata, de esta manera si hay un observador, este recibirá la nueva lista
        livedata_numbers.setValue(numbers)
        // La mostramos en el logcat
        Log.d(TAG_LOG, "Añadimos Array al livedata:" + numbers.toString())
    }


}