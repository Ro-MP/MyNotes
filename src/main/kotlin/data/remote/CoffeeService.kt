package data.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class CoffeeService {
    val URL = "https://api.sampleapis.com/coffee/hot"
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun getHotCoffeeList(): Flow<CoffeeAPI> {
        val hotCoffeeResponse: List<CoffeeAPI> = try {
             client.get(URL).body()
        } catch (e: Exception) {
            println(e.message)
            listOf()
        }
        return flow {
            hotCoffeeResponse.forEach {
                kotlinx.coroutines.delay(500)
                emit(it)
            }
        }
    }

}