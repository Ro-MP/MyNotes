package courseNotes

fun main() {


    /***
     *  Collections
     */

/*
    List
 */

    val list: List<String> = listOf(
        "hola",
        "Mundo",
        "Hello",
        "World"
    )
    val list2 = list + "Holis"
    print(list2)

    val result = list2.filter {
        !it.contains("Mundo") && !it.contains("World")
    }.sorted()
    print(result)

    val emptyList: List<String> = emptyList()

    val listMut = mutableListOf("Hi")
    listMut.add("aloha")
    print(listMut)


/*
    Set
 */

    val set = setOf("title 1", "title 2", "title 1")
    print(set)

/*
    Map
 */

    val map = mapOf<Int, String>(
        Pair(1, "123"),
        Pair(2, "234"),
        Pair(3, "345")
    )
    print(map)

    val map2 = mapOf<Int, String>(
        1 to "123",
        2 to "234",
        3 to "345"
    )
    print(map2)

/*
    Rango
 */

    (0 .. 10)
    (0 .. 10).map { "Item $it" }

    (0 .. 10 step 2)
    (20 downTo 2 step 2).map { "$it" }
    (0 until 10)
    ('a' .. 'f').toList()

    (1.0 .. 2.0)
    val double = 1.7
    double in (1.0 .. 2.0)
    double in (3.0 .. 4.0)

}

