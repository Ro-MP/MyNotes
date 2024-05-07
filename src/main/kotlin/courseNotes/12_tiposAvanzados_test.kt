package courseNotes

import data.notes.Note
import data.notes.NoteType


fun main(){
//    nothingTest()
    genericTest()
}

fun nothingTest(){
    val c: Int? = null
    val x: Int = c ?: throw KotlinNullPointerException("c on Test(c: Int?) cant be null")
}

fun genericTest(){
    Encrypter(Note("Nota", "Nota", NoteType.EVENT)).run().println()
    Encrypter(NoteType.REMINDER).run().println()
}

class Encrypter<T>(val item: T) {
    fun run(): String{
        return item.toString()
    }

}

fun String.println(){
    println(this)
}
fun String.print(){
    print(this)
}

interface encriptable {
    fun encriptToString()
}

/*
    Nothing
    - Son tipos que heredan de todos los tipos
    - Implica un corte de flujo de ejecicion
    - Se retornan de funciones TODO(), Exception(), return

    Tipos Genericos
    - Generan soluciones para que sirvan distintos tipos de datos
    - <T : Any> Cualquier tipo no nulo

 */