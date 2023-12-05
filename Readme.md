
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

