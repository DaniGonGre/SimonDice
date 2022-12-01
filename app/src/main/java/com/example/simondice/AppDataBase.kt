package com.example.simondice

import androidx.room.*

class AppDataBase {

    @Database(entities = [Dato::class], version = 1)

    abstract class AppDataBase : RoomDatabase() {
        abstract fun datosDao(): DatosDAO
    }

}