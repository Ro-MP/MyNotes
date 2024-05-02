package courseNotes

fun main() {
    classesTest()
    interfacesTest()
    dataClassTest()
    enumTest()
    objetctSingletonTest()
    objectInterfacesTest()
    CompanionObjecttest()
}

/***
 *  Classes
 */


open class Person(name: String, var age: Int){
    val name = name
        get() = "This is the mathafaka $field"
}

class Developer(name: String, age: Int) : Person(name, age)

fun classesTest(){
    val person = Person("Rodrigo", 27)
    val developer = Developer("Garnacha", 34)

    person.name
    developer.name
}



/*
 *  Interfaces
 */

interface CanWalk {
    fun doStep()

    fun walk(steps: Int){
        repeat(steps){
            doStep()
        }
    }
}

class Dog : CanWalk {
    override fun doStep() {
        print(" - step - ")
    }
}

fun interfacesTest(){
    val dog1 = Dog()

    dog1.walk(5)
}



/*
 * Data Classes
 */

data class Being(val name: String, val age: Int)

fun dataClassTest() {
    val b1 = Being("Roy", 27)
    val b2 = Being("Roy", 27)

    println(b1.equals(b2))
    println(b1==b2)
    println(b1===b2)

    val p3 = b2.copy(name = "Rodrigo")
    b1.toString()

    val (a, b) = b2
    println(a)
    println(b)
}


/*
 * Enums
 */

enum class Shape(val numberOfSides: Int){
    SQUARE(4),
    RECTANGLE(4),
    TRIANGLE(3),
    OVAL(1)
}
fun enumTest(){
    val shape: Shape = Shape.TRIANGLE
    println(shape)
    println(shape.ordinal)
    println(shape.numberOfSides)

    val shapes = Shape.values()
    println(shapes[1])

    println(Shape.valueOf(Shape.OVAL.toString()))
}



/*
 * Objects -> Singletons
 * Se crea hasta que se utiliza por primera vez
 */

object Database {
    private val items = mutableListOf<Int>()

    fun getAll(): List<Int> = items
    fun save(value: Int){
        items.add(value)
    }
    fun save(value: Collection<Int>){
        items.addAll(value)
    }
}

fun objetctSingletonTest(){
    Database.save(2)
    Database.save((1..5).toList())
    Database.getAll()

    val obj = object  {
        val x = 1
        val y = 2

        fun suma(z: Int) = x+y+z
    }
}




/*
 * Objects - Implement interface
 */

interface Call{
    fun invoke()
}

fun objectInterfacesTest(){
    fun foo(call: Call) { }

    foo(object : Call {
        override fun invoke() {
            TODO("Not yet implemented")
        }
    })
}


/*
 * companion Objects
 * Objeto que va a acompañar a todas las instancias de una clase
 */

class Cellphone(val brand: String){
    //    companion object {
//        var company: String = "Telcel"
//    }
    companion object MyCompanion{
        var company: String = "Telcel"
    }
    fun printData(){
        print("Brand: $brand, Company: $company")
    }
}

fun CompanionObjecttest() {
    val iphone = Cellphone("Iphone")
    val sonny = Cellphone("Sonny")

    iphone.printData()
    sonny.printData()

//Cellphone.MyCompanion.company = "AT&T"
    Cellphone.company = "AT&T"
    iphone.printData()
    sonny.printData()
}


