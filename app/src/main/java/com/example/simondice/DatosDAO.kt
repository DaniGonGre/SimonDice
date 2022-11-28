package com.example.simondice

import androidx.room.*

    @Dao
    interface DatosDAO {
        @Query("SELECT*FROM Dato WHERE ronda=(SELECT MAX(ronda) FROM Dato)")
        fun datoMayor(mayor:Int): Dato

        @Insert
        fun insertar(datos: Dato)
    }


