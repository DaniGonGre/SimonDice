package com.example.simondice

import androidx.room.*

    @Entity
    data class Dato(
        @PrimaryKey val id: Int,
        @ColumnInfo(name = "ronda") val ronda: Int

    )

