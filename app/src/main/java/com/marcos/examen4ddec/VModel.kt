package com.marcos.examen4ddec


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class VModel :ViewModel(){



    init {
        aux()
    }


    fun verificarRespuesta(respuesta: Boolean) {
        if (!Data.juegoIniciado.value) return

        if (Data.fraseActual.value.verdadero == respuesta) {
            Data.fraseActual.value = Data.frases.random()
            Data.puntuacion.value++
        } else {
            Data.fraseActual.value = Data.frases.random()
            Data.puntuacion.value = 0
        }
    }



    private fun aux() {
        // Introducir frases en la lista
        Data.frases.add(Data.Frase("el torneo de rugby cinco naciones, ahora es seis naciones", true))
        Data.frases.add(Data.Frase("en el cielo hay cinco estrellas", false))
        Data.frases.add(Data.Frase("el dia cinco de diciembre del 2023 es martes", true))
        Data.frases.add(Data.Frase("cinco más cinco son diez", true))
        Data.frases.add(Data.Frase("dos mas dos son cinco", false))
        Data.frases.add(Data.Frase("los elefantes tienen cinco patas", false))
        Data.frases.add(Data.Frase("las estaciones climáticas son cinco", false))
        Data.frases.add(Data.Frase("tenemos cinco dedos los humanos", true))
        Data.frases.add(Data.Frase("cinco días tiene la semana sin el Domingo y el Sábado", true))
        Data.frases.add(Data.Frase("una gallina pesa menos que cinco toneladas", true))
    }
}