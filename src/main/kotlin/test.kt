import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@InternalCoroutinesApi
fun main(): Unit = runBlocking {

    val flow = flow {
        emit(1)
        kotlinx.coroutines.delay(2000)
        emit(2)
        kotlinx.coroutines.delay(2000)
        emit(3)
        kotlinx.coroutines.delay(1000)
    }.transform {
        if (it%2 == 0) emit(it*10)
        else emit(it)
    }
    kotlinx.coroutines.delay(2000)

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

}


@InternalCoroutinesApi
suspend fun <T> Flow<T>.collect(emit: (T)->Unit){
    this.collect(object : FlowCollector<T>{
        override suspend fun emit(value: T) {
            emit(value)
        }
    })
}