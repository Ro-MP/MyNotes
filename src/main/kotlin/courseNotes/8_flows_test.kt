import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*


@InternalCoroutinesApi
fun main(): Unit = runBlocking {

    /***
     * Flows
     **/

    val floww = flowOf(1,2, 3, 4)

    val flow2w = listOf("a", "b", "c").asFlow()

    val flow3 = flow {
        emit(1)
        emit(2)
    }

    val f = floww.zip(flow2w) { a, b ->
        "$a  >>  $b"
    }

    val flow = flow {
        emit(1)
        delay(1000)
        emit(2)
        delay(1000)
        emit(3)
        delay(500)
    }.transform {
        if (it%2 == 0) emit(it*10)
        else emit(it)
    }
    delay(1000)

    launch {
        flow.collect {
            println(it)
        }
    }
//    launch {
//        flow.collect {
//            println(it)
//        }
//    }

    val flow2 = flowOf("a", "b", "c")

    flow.zip(flow2){ a,b ->
        "$a >> $b"
    }.collect {
        println(it)
    }

    flow.combine(flow2){ a,b ->
        "$a -> $b"
    }.collect {
        println(it)
    }



    /**
     * >>> ShareFlow
     */

    val shared = MutableSharedFlow<Int>(
        replay = 3,  // Cuantos valores se quiere almacenar
        extraBufferCapacity = 2,  // Valores extra que se continuan emitiendo hasta 1ue se empiecen a recolectar los del replay
        onBufferOverflow = BufferOverflow.DROP_OLDEST  // Decide que hacer con los valores extra
    )
    suspend fun updateShared() {
        var count = 0
        while (count < 6) {
            delay(500)
            count++
            shared.emit(count)
        }
    }

    launch {
        updateShared()
    }

    shared.map { " SharedFlow: $it " }.collect(::println)

    /**
     * >>> StateFlow
     */

    val state = MutableStateFlow(0)

    suspend fun updateState() {
        var count = 0
        while (count < 6) {
            delay(500)
            count++
            state.value = count
        }
    }

    launch {
        updateState()
    }
    delay(2000)

    state.map { " StateFlow: $it " }.collect(::println)



}


@InternalCoroutinesApi
suspend fun <T> Flow<T>.collect(emit: (T)->Unit){
    this.collect(object : FlowCollector<T>{
        override suspend fun emit(value: T) {
            emit(value)
        }
    })
}


/*
 * >>> Flows
 * - Son secuencias asincronas que cada valor puede ser una peticion al server o db
 * - Son lazy. Son cold streams, no emiten datos hasta que no haya nadie recolectando
 * - Si un elemento se conecta al flow este emitirá desde el primer valor del flujo. Por lo que
 *      si hay operaciones pesadas del flow estas se repetirán cada que alguien recolecte sus valores
 * - Son secuenciales, uno tras de otro
 * -
 *
 * Operaciones de transformacion
 * - map, forEach, etc
 * - .trasform{} -operacion de transformacion mas compleja
 * - Flow<T>.zip(Flow<T2>){} - une los dos flows y se completa cuando un flow se complete
 * - Flow<T>.cobine(Flow<T2>){} - Cobina los flows hasta que se terminen ambos
 *
 * Operaciones terminales
 *  - .collect
 *  - .toList  -> Espera a que se tengan todos los valores para emitir una lista con ellos
 *  - .first
 *  - .reduce
 *  - etc
 *
 *  - Dentro del Flow no se puede cambiar de contexto
 *  - .flowOn() - Permite cambiar el contexto
 *
 *  - No usar try catch dentro del flow
 *  - .catch{}
 *
 *
 *  >>> State Flows
 *  - Son Hot Streams -> Los valores se van a emit independientemente de que haya alguien escuchandolos
 *  - Almacena el ultimo valor emitido
 *
 *  >>> SharedFlow
 *  - Mucho mas flexible que state flow
 *  - Puede emitir aunque el valor  no cambie, aunque se puede ocupar
 *      distinctUntilChanged()
 *
 *  >>> Channel
 *  - Lo que se utilizaba antes que flows
 *
 *
 *
 */