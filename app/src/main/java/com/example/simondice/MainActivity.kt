package com.example.simondice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    //Datos
    var secuencia = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Variables
        var random = (0..3).random()
        val colores = arrayOf("verde", "amarillo", "azul", "rojo")

    }
}