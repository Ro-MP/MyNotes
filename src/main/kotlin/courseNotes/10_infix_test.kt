package courseNotes

fun main() {
    var p = "a" to 3

    val lunch1 = "Rodrigo".eats("Tlacoyos")
    lunch1.sayLunch()

    val lunch2 = "Nans" eats "Burgers"
    lunch2.sayLunch()

}

infix fun String.eats(that: String): Lunch = Lunch(this,that)

class Lunch(
    private val name: String,
    private val food: String
){
    fun sayLunch(){
        println("$name will eat $food")
    }
}