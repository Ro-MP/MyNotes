package courseNotes

fun main() {
    testSealedClassWalk(Swan())
    testSealedClassFly(Fly())
}

sealed class CanWalkSealed(val legsAmount: Int)
sealed interface CanFly

class Elephant(val name: String): CanWalkSealed(4)
class Spider(val childsAmount: Int): CanWalkSealed(8)
class Swan: CanWalkSealed(2), CanFly
class Fly: CanFly


fun testSealedClassWalk(canWalk: CanWalkSealed) {
    when(canWalk) {
        is Elephant -> println(canWalk.name)
        is Spider -> println(canWalk.childsAmount)
        is Swan -> println(canWalk::class.simpleName)
    }
}

fun testSealedClassFly(canFly: CanFly) {
    when(canFly) {
        is Fly -> println(canFly::class.simpleName)
        is Swan -> println(canFly::class.simpleName)
    }
}



/*

Sealed classes
    - Enumerados -> El compilador sabe qcuantos enumerados tenemos
    - Classes -> Permite guardar estado y distintas instancias


Sealed interfaces
    - Siempre que se pueda usar interfaces para usar composicion
    - No permite almacenar estados

    - Todas las subclases de sealed tienen que estar en el mismo paquete


 */