package courseNotes

fun main() {
    var x = 20
    x = 30
    println(x)

    val z: Long = x.toLong()
    println(z)

    val double = 20.0
    val float = 10f
    val int = 100_000
    val long = 500L


    val s = "20"
    val hello = "Hello, I have $s dollars"

    var xnull : Int? = 20
    xnull = null
    println(xnull?.toLong())
}