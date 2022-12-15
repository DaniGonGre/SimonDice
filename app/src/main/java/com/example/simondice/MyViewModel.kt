package com.example.simondice

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
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

    private val tag = "RealTime"

    private var database_record: DatabaseReference

    /*
    // Nos conectamos a la base de datos con un constructor
    val db = Room.databaseBuilder(
        getApplication<Application>().applicationContext,
        AppDataBase::class.java, "datosBD"
    ).build()
    */

    init {
        ronda.value = 0
        record.value = 0
    /*
        //recogiendo los datos con SQLite con una corrutina

        val Corrutine = GlobalScope.launch(Dispatchers.Main) {

            try {
                ronda.value = db.datosDao().getRonda()
            } catch (ex: java.lang.IndexOutOfBoundsException) {
                ronda.value = db.datosDao().getRonda()
            }

        }

        Corrutine.start()
    */
        //acceso a la BD Firebase

        database_record = Firebase.database("https://simondice-f1cd8-default-rtdb.firebaseio.com/")
            .getReference("record")

        //definición del listener

        val recordListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                record.value = dataSnapshot.getValue<Int>()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.d(tag, "recordListener:OnCancelled", error.toException())
            }
        }

        //se añade el listener a la BD
        database_record.addValueEventListener(recordListener)
    }

    fun sumarRonda() {
        ronda.value = ronda.value?.plus(1)
    }

    fun reiniciarRonda() {
        ronda.value = 0
    }

    fun sumarRecord() {
        record.value = record.value?.plus(1)
    }

}


