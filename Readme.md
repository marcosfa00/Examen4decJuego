
# REFACTORIZACIÓN DE CÓDIGO MVVM

EL primer paso será crear una nueva clase a la que he llamado VModel, puesto que esta clase más tarde instanciará la clase ViewModel

Aquí será donde tendremos que rehacer todas las funciones a las cuales llaman nuestros botones en esta clase, esto
Hará que nuestro código sea Mucho más ordenado y legible.

También vamos a tener una clase de tipo Data, donde instanciaremos las variables

# DATA

```Kotlin

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

    
  ```

# VMODEL

A continuación procedemos a Crear la clase VModel, donde vamos a crear una función por el código que hagamos en la UI

donde cambiaremos un código como teníamos en el Boton Respuesta

```Kotlin
if (!juegoIniciado) return@Button
if (fraseActual.value.verdadero == verdadero) {
fraseActual.value = frases.random() // Selecciona una nueva frase correcta
puntuacion++
} else {
fraseActual.value = frases.random() // Selecciona una nueva frase incorrecta
}
```

Por este otro código

```Kotlin
 miModel.verificarRespuesta(verdadero)
```

Y el codigo de la función verificarRespuesta se encuentra en la clase VModel, aquí hemos cambiado un parametro en todos los
elementos de tipoo Composable, y es que ahora también le debemos pasar cómo parametro un objeto del tipo **VModel** para poder llamar
a la función 

También esta función como llama a Juego iniciado, frase actual, etc, debemos cambiar el código de la función para que llame a las variables que se encuentran
en la clase d etipo objecto **Data**
```Kotlin

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
``` 



El juego crea una corrutina, ahroa esta debe esatr en el VModel, por lo que esta clase debe extender de ViewModel

```Kotlin
class VModel :ViewModel(){}

fun iniciarJuego() {
    Data.juegoIniciado.value = true
    Data.fraseActual.value = Data.frases.random()
    Data.juegoScope = CoroutineScope(Dispatchers.Default)
    Data.juegoScope?.launch {
        repeat(20) {
            delay(1000)
            Data.cuentaAtras.value--
        }
        Data.juegoIniciado.value = false
    }
}
```


Una de las cosas que he cambiado es que AUX ya no es una función @Composable, sino que es una función normal, ya que no se va a llamar desde la UI

# interfaz de usuario

a continuación cambiamos toda la interfaz d eusuario, de manera que solo se tengan que llamar a las funciones que hemos hecho en el VIewModel

por ejemplo en en Greeting, cambiamos el código de esta manera

```Kotlin

 // Botón para iniciar o reiniciar el juego
        Button(
            onClick = {
                miModel.resetGame()
                miModel.iniciarJuego()
            }
        ) {
            Text("START")
        }

//Al igual que el onClick del boton VF, cambiamos el código por el siguiente
@Composable
fun BotonRespuesta(miModel: VModel, verdadero: Boolean, color: Color) {
    Button(
        onClick = {

            miModel.verificarRespuesta(verdadero)
        }) 
```







