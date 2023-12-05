package com.marcos.examen4ddec

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope


//Ruta: /app/src/main/java/com/marcos/myaplicationpractica
    object Data {

        val fraseActual: MutableState<Frase> = mutableStateOf(Frase("-", true))
        val cuentaAtras: MutableState<Int> = mutableStateOf(20)
        val puntuacion: MutableState<Int> = mutableStateOf(0)
        val juegoIniciado: MutableState<Boolean> = mutableStateOf(false)
        //Esat esatba declarada como private
        var juegoScope: CoroutineScope? = null
        // Para crear los objetos de las frases
        data class Frase(var texto: String, var verdadero: Boolean)
        // Lista para almacenar las frases
        var frases: MutableList<Frase> = mutableListOf()
        // La frase actual



    }

