package courseNotes

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun main(){


    data class User(
        val name: String,
        val password: String,
        val friends: MutableList<User> = emptyList<User>().toMutableList()
    ){

        suspend fun requestCurrentFriends(numberOfFriends: Int) = withContext(Dispatchers.IO)  {
            delay(3000L)
            repeat(numberOfFriends){
                friends.add( User("Friend $it", "Password $it") )
            }
        }
    }


    class UserService {
        suspend fun doLogin(userName: String, password: String): User = withContext(Dispatchers.IO) {
            delay(3000L)
            User(userName, password)
        }
    }

    fun test() {
        val userService = UserService()
        GlobalScope.launch(Dispatchers.Main) {
            print("Starting")
            val user = userService.doLogin("Rodrigo", "hola12345")
            val user1 = userService.doLogin("Ro1", "hola12345")
            user.requestCurrentFriends(3)
            user1.requestCurrentFriends(3)
            print("Ending")
        }

        val job = GlobalScope.launch(Dispatchers.Main) {
            print("Starting")
            val user2 = userService.doLogin("Garnacha", "garnacha12345")
            val user3 = userService.doLogin("Mike", "wazausky12345")
            val d: Deferred<Unit> = async { user2.requestCurrentFriends(3) }
            val c: Deferred<Unit> = async{ user3.requestCurrentFriends(3) }
            print("Ending")
            c.await()
            d.await()
            print("Hey bros")
        }
        //job.join()

        val coroutineScope = object : CoroutineScope {
            val job = Job()

            override val coroutineContext: CoroutineContext
                get() = Dispatchers.Main
        }

        coroutineScope.launch {
            print("Starting")
            val user2 = userService.doLogin("Garnacha", "garnacha12345")
            val user3 = userService.doLogin("Mike", "wazausky12345")
            val d: Deferred<Unit> = async { user2.requestCurrentFriends(3) }
            val c: Deferred<Unit> = async{ user3.requestCurrentFriends(3) }
            print("Ending")
            c.await()
            d.await()
            print("Hey bros")
        }

        coroutineScope.job.cancel()  // Cuando el ciclo de vida de la pantalla, componente, finalice

        val coroutineScope2 = object : CoroutineScope {
            val job = SupervisorJob()   // Si un operacion falla no se cancela el resto

            override val coroutineContext: CoroutineContext
                get() = Dispatchers.Main
        }
        coroutineScope2.job.cancel()

        val coroutineScope3 = MainScope()
        coroutineScope3.coroutineContext.cancel()

        val coroutineScope4 = CoroutineScope(Dispatchers.IO)
        coroutineScope.job.cancel()

    }

    test()

}


/**
Dispatcher:
Define hilo o conjunto de hilos para ejecutar corrutinas de ese contexto
- Dispatcher.Default  >> Operaciones para uso intensivo de la CPU
- Dispatcher.IO  >>  Operaciones para db, ficheros, redes, sensores
- Dispatcher.Main  >>  Hilo principal para UI
- Dispatcher.Unconfined  >>  Inicialmente para testing pero ya casi no se usa
 **/

/**
Builder:
Permite crear corrutina
- runBlocking  >> Bloquea el hilo hasta que el codigo de adentro haya finalizado
- launch   >>  No bloquea Hilo, builder básico, devuelve un job, se necesita un scope
- async >> Tiene que ser llamado dentro de otro constructor, la ejecucion no se detine y espera, pasa a ejecutarse la linea siguiente
Tiene un job de tipo Deferred, que tiene funcion await(), cuando se llame nos quedamos suspendidos esperando el resultado
 **/

/**
Job:
- join()  >> Funcion suspend, Permite que una corrutina acabe antes de seguir con la otra
- cancel()   >>  Cancela corrutinas enlazadas con el Job
 **/

/**
Scope:
Ambito donde se ejecuta y aplica una corrutina
- GlobalScope   >> Activo en todo el ciclo de vida de la app
 **/