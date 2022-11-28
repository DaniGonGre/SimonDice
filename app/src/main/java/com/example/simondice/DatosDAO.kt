package com.example.simondice

import androidx.room.*

class DatosDAO {

    @Dao
    interface RondaDAO {
        @Query("SELECT*FROM Datos WHERE ronda=(SELECT MAX(ronda) FROM Datos)")
        fun datoMayor(mayor:Int): Dato

        @Insert
        fun insertar(datos: Dato)
    }


}