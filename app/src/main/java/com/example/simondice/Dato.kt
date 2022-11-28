package com.example.simondice

import androidx.room.*

class Dato {

    @Entity
    data class Datos(
        @PrimaryKey val id: Int,
        @ColumnInfo(name = "ronda") val ronda: Int

    )

}