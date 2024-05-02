package courseNotes

fun main() {
    /***
     * Secuencias
     **/

// Las operaciones intermedias se ejecutan en cada elemento en vez de generar un objeto intermedio


    val sequence = sequenceOf("one", "two", "three")

    sequence.map {  }


    val lista = listOf("Hola", "Hello", "Hi")

    val result = lista.asSequence()
        .map {  }
        .filter { true }
        .toList()


    val sequence2 = generateSequence(2) { it*2 }

    generateSequence(2) {  it * 2 }
        .takeWhile { it < 10_000 }
        .sorted()
        .toList()

    sequence {
        yield(3)
        yieldAll(listOf(2, 20, 35))
        yieldAll(generateSequence(2) {  it * 2 }
            .takeWhile { it < 10_000 })
    }.toList()

}

/*
 * -> Stateless
 *   Operaciones que no necesitan estado intermedio para calcular sus valores
 *   O una cantidad pequeña y constante
 *   ej. map, filter, take, drop
 * -> Stateful
 *   Requiere gran cantidad de memoria para procesarse, usualmente
 *   equivalente al numero de valores de la secuencia
 *   ej. sorted, distinct,
 *
 *
 * -> Operaciones intermedias
 *   ej. map, filter, etc
 * -> Operaciones terminales
 *   Tiene que iterar con todos los elementos
 *   ej .toList, .sum
 */