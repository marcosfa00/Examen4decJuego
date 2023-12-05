package com.marcos.myaplicationpractica


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcos.examen4ddec.Data
import com.marcos.examen4ddec.VModel








/**
 * Composable que representa la interfaz de usuario principal del juego.
 * Muestra el contador de cuenta atrás, la puntuación actual, la frase del juego,
 * botones de respuesta (Verdadero o Falso) y un botón para iniciar o reiniciar el juego.
 *
 * @param miModel El ViewModel asociado para manejar la lógica del juego.
 */
@Composable
fun Greeting(miModel: VModel) {

    val cuentaAtras by remember { Data.cuentaAtras }
    val puntuacion by remember { Data.puntuacion }
    val fraseActual by remember { Data.fraseActual }

    // Composición de la interfaz de usuario con Jetpack Compose
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Muestra el contador de cuenta atrás
        Text(
            text = "Count Down: $cuentaAtras",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Muestra la puntuación actual
        Text(
            text = "Puntuación: $puntuacion",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Muestra la frase actual del juego
        Text(
            text = "Frase: ${fraseActual.texto}",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botones de respuesta (Verdadero o Falso)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BotonRespuesta(miModel, verdadero = true, color = Color.Green)
            BotonRespuesta(miModel, verdadero = false, color = Color.Red)
        }

        // Botón para iniciar o reiniciar el juego
        Button(
            onClick = {
                miModel.resetGame()
                miModel.iniciarJuego()
            }
        ) {
            Text("START")
        }
    }
}


/**
 * Composable que representa un botón de respuesta que puede ser Verdadero (V) o Falso (F).
 *
 * @param miModel El ViewModel asociado para manejar la lógica de la respuesta.
 * @param verdadero Booleano que indica si el botón representa una respuesta verdadera (true) o falsa (false).
 * @param color El color que se aplicará al botón.
 */
@Composable
fun BotonRespuesta(miModel: VModel, verdadero: Boolean, color: Color) {
    Button(
        onClick = {

            miModel.verificarRespuesta(verdadero)
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
                fontSize = 20.sp
            )
        }
    }
}
