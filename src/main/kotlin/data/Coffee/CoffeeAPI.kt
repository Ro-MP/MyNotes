package data.Coffee

import kotlinx.serialization.Serializable

@Serializable
data class CoffeeAPI(
    val title: String,
    val description: String,
    val ingredients: List<String>,
    val image: String,
    val id: Int
)