package courseNotes

import kotlinx.coroutines.processNextEventInCurrentThread

fun main() {

    funcionLocalTest()

    /***
     *  Functions
     */

    fun sum(x:Int = 1, y:Int = 1) : Int {
        return x + y
    }

    sum(2, 5)

/*
 *  Lambdas
 */
// (A, B) -> C

    val f: (Int, Int) -> Int = { x, y ->
        x + y
    }

    val g = { x: Int, y: Int ->
        x + y
    }

    f(2, 3)
    g(3, 2)

    fun doOp(x: Int, y: Int, op: (Int, Int)->Int) =
        op(x,y)

    doOp(2, 3, f)
    doOp(2, 3){ x, y ->
        x * y
    }

/*
 *  Entension functions
 */

    fun String.take3FirstCharacter() : String{
        return this.take(3)
    }

    val str = "Hola Mundo"
    str.take3FirstCharacter()

/*
 *  Scope functions
 */

    val strBuilder = StringBuilder()
    val res = with(strBuilder){
        append("Hello")
        append(" ")
        append("World")
        toString()
    }
    res

    val res2 = with(StringBuilder()){
        append("Hello")
        append(" ")
        append("World")
        toString()
    }
    res2

    val res3 = StringBuilder().run{
        append("Hello")
        append(" ")
        append("World")
        toString()
    }
    res3

    val res4 = StringBuilder().apply{
        append("Hello")
        append(" ")
        append("World")
    }
    res4

    var str85 : String? = "Hola mundo"
    str85?.let {
        print(it)
    }
    str85?.let(::print)   // Referencia a la funcion en vez de llamar a la funcion

    fun op(str: String): String{ return str }

    str85?.let(::op)
        .also(::print)
}

/*
        Funciones Locales
        Son funciones dentro de funciones que pueden acceder al estado de la
         funcion superior
     */

fun funcionLocalTest() {
    val x = 10

    fun interna(param: Int): Int{
        return param + x
    }

    println("Funcion interna de 5: ${interna(5)}")
    println("Funcion interna de 10: ${interna(10)}")
}