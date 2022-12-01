package com.example.simondice

import androidx.room.*

    @Dao
    interface DatosDAO {

        //Seleccionamos la ronda mayor
        @Query("SELECT ronda FROM Dato WHERE id = 1")
        suspend fun getRonda(): Int

        //Actualizamos la base de datos
        @Update
        suspend fun actualizar(datos: Dato)
    }


