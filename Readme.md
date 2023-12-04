
# Juego de Frases - Aplicación con Compose

Esta aplicación de Android está desarrollada con Jetpack Compose y ofrece un juego de preguntas de verdadero o falso basado en una serie de frases. A continuación, se presenta una visión general de la estructura y funcionalidades principales de la aplicación:

## Descripción General

La aplicación consiste en un juego de frases donde el jugador debe determinar si una afirmación dada es verdadera o falsa. La interfaz de usuario está compuesta por frases aleatorias, temporizador y botones de respuesta.

## Estructura del Código

El código se organiza en varias secciones clave:

- **Variables Globales**: Contiene la información sobre frases, puntuación, temporizador y el estado del juego.
- **Función Auxiliar `aux()`**: Inicializa la lista de frases con afirmaciones verdaderas y falsas.
- **Función `Greeting()`**: Define la interfaz de usuario del juego utilizando Jetpack Compose, mostrando el temporizador, la puntuación y las frases
- **Función `BotonStart()`**: Define el botón de inicio del juego y su comportamiento al presionarlo.
- **Función `BotonRespuesta()`**: Define los botones de respuesta (Verdadero o Falso) y su comportamiento al seleccionar una respuesta.

## Codigo Boton Start

```kotlin
@Composable
fun BotonStart() {
    Button(
        onClick = {
            start = true
            aux()
        },
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFBB86FC)
        )
    ) {
        Text(text = "Start")
    }
}
```

## Codigo Boton Respuesta

```kotlin
@Composable
fun BotonRespuesta(respuesta: Boolean) {
    Button(
        onClick = {
            if (respuesta == respuestaActual) {
                puntuacion++
            }
            aux()
        },
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFBB86FC)
        )
    ) {
        Text(text = if (respuesta) "Verdadero" else "Falso")
    }
}
```

## Usamos las siguientes variables globales

```kotlin

/**
 * Variables globales
 */
// Para crear los objetos de las frases
data class Frase(var texto: String, var verdadero: Boolean)
// Lista para almacenar las frases
var frases: MutableList<Frase> = mutableListOf()
// La frase actual
var fraseActual: MutableState<Frase> = mutableStateOf(Frase("-", true))
var CuentaAtras by mutableStateOf(20)
var puntuacion by mutableStateOf(0)
var juegoIniciado by mutableStateOf(false)

```

## Uso de la Aplicación

Para jugar a este juego, se siguen los siguientes pasos:

1. **Inicio del Juego**: Al abrir la aplicación, presiona el botón "Start" para iniciar el juego.
2. **Temporizador y Puntuación**: Observa el temporizador que cuenta desde 20 hasta 0 segundos y la puntuación que aumenta al responder correctamente.
3. **Selecciona Respuestas**: Haz clic en los botones "V" (verdadero) o "F" (falso) para responder a la afirmación presentada.
4. **Finalización del Juego**: El juego termina cuando el temporizador llega a cero.



