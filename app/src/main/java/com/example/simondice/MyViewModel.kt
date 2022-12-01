package com.example.simondice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Inicializa y modifica los datos de la app
 */
class MyViewModel(application: Application) : AndroidViewModel(application) {

    // Creamos una constante privada para darle cambiarle la etiqueta al log
    private val TAG_LOG: String = "mensaje ViewModel"

    // Este va a ser nuestra lista para la ronda, usamos mutable para modificar
    val ronda = MutableLiveData<Int>()

    // Este va a ser nuestra lista para el record, usamos mutable para modificar
    var record = MutableLiveData<Int>()

    // Nos conectamos a la base de datos con un constructor
    val db = Room.databaseBuilder(
        getApplication<Application>().applicationContext,
        AppDataBase::class.java, "datosBD"
    ).build()

    // Inicializamos variables cuando instanciamos
    init {
        ronda.value = 0

        val Corrutine = GlobalScope.launch(Dispatchers.Main) {

            try {
                ronda.value = db.datosDao().getRonda()
            } catch (ex: java.lang.IndexOutOfBoundsException) {
                ronda.value = db.datosDao().getRonda()
            }

        }

        Corrutine.start()

    }

    /**
     * Función que muestra la ronda
     */
    fun actualizarRecord() {
        /*
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
        */

        record.value = ronda.value

        val actualizarCorrutine = GlobalScope.launch(Dispatchers.Main) {
            db.datosDao().actualizar(Dato(1, ronda.value!!))
        }
        actualizarCorrutine.start()
    }

    fun actualizarRonda() {
        ronda.value = ronda.value?.plus(1)
    }

    fun resetRonda() {
        val resetCorrutine = GlobalScope.launch(Dispatchers.Main) {
            db.datosDao().actualizar(Dato(1, 0))
        }
        resetCorrutine.start()
    }


}