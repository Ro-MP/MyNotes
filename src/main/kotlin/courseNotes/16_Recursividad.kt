package courseNotes

tailrec fun factorial(n: Int, accum: Long = 1): Long {
    val soFar = n* accum
    return if (n <= 1) {
        soFar
    } else {
        factorial(n-1, soFar)
    }
}