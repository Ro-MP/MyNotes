package courseNotes

import courseNotes.Test.Type.Tipe1
import courseNotes.Test.Type.Type2 as secondType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

fun lamb(f: String.() -> String) {}

fun test() {
    lamb {
        uppercase()
    }
}

typealias ElMeroCaguameroTest = Test

data class Test(val title: String, val type: Type) {
    enum class Type { Tipe1, Type2 }
    companion object
}

val Test.Companion.fakeTests
    get() = flow {
        delay(1000)
        val tests = (0..20).map {
            Test(
                "Title $it",
                if (it%3 == 0) Tipe1 else secondType
            )
        }
        emit(tests)
    }

fun test2() {
    val tests = Test.fakeTests
    val tests2 = ElMeroCaguameroTest.fakeTests

}