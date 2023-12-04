
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.*

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

/**
 * Función auxiliar con las frases verdaderas y falsas
 */
@Composable
fun aux() {
    // Introducir frases en la lista
    frases.add(Frase("el torneo de rugby cinco naciones, ahora es seis naciones",true))
    frases.add(Frase("en el cielo hay cinco estrellas",false))
    frases.add(Frase("el dia cinco de diciembre del 2023 es martes",true))
    frases.add(Frase("cinco más cinco son diez",true))
    frases.add(Frase("dos mas dos son cinco",false))
    frases.add(Frase("los elefantes tienen cinco patas",false))
    frases.add(Frase("las estaciones climáticas son cinco",false))
    frases.add(Frase("tenemos cinco dedos los humanos",true))
    frases.add(Frase("cinco días tiene la semana sin el Domingo y el Sábado",true))
    frases.add(Frase("una gallina pesa menos que cinco toneladas",true))
}

@Composable
        /**
         * Función de la Grretting
         */
fun Greeting() {

    aux() // Llama a la función auxiliar para inicializar las frases

    val scope = rememberCoroutineScope()

    /**
     * Función para iniciar el juego
     */
    fun iniciarJuego() {
        juegoIniciado = true
        fraseActual.value = frases.random() // Selecciona una frase aleatoria
        scope.launch {
            repeat(20) {
                delay(1000)
                CuentaAtras--
            }
            juegoIniciado = false
        }
    }

    // Composición de la interfaz de usuario con Jetpack Compose
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Count Down: $CuentaAtras",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Puntuación: $puntuacion",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Frase: ${fraseActual.value.texto}",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BotonRespuesta(verdadero = true, color = Color.Green)
            BotonRespuesta(verdadero = false, color = Color.Red)
        }
        Button(
            onClick = {
                if (!juegoIniciado) {
                    CuentaAtras = 20
                    puntuacion = 0
                    iniciarJuego()
                }
            }
        ) {
            Text("Start")
        }
    }
}

/**
 * Composición de un botón de respuesta (Verdadero o Falso)
 */
@Composable
fun BotonRespuesta(verdadero: Boolean, color: Color) {
    Button(
        onClick = {
            if (!juegoIniciado) return@Button
            if (fraseActual.value.verdadero == verdadero) {
                fraseActual.value = frases.random() // Selecciona una nueva frase correcta
                puntuacion++
            } else {
                fraseActual.value = frases.random() // Selecciona una nueva frase incorrecta
            }
        },

        modifier = Modifier
            .padding(10.dp)
            .size(150.dp)
            .padding(8.dp), // Añadir padding para que se vean mejor los bordes redondeados
        shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp), // Agregar bordes redondeados

        colors = ButtonDefaults.buttonColors(color)
    ) {
        if (verdadero) {
            Text("V",
                    color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )

        } else {
            Text("F",
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp)
        }
    }
}