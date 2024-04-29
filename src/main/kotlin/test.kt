import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*


@InternalCoroutinesApi
fun main(): Unit = runBlocking {

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