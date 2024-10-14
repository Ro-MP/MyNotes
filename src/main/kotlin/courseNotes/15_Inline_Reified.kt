package courseNotes


val list = listOf(2, 10.0, "Hola", 14f)

inline fun <reified T> List<Any>.filterIsInstance2(): List<T> {
    val res = mutableListOf<T>()
    forEach { item ->
        if (item is T) {
            res.add(item)
        }
    }

    return res
}