package courseNotes

import kotlin.properties.Delegates

fun main() {

    /***
     * Delegación de propiedades
     */

/*
 * getters & setters
 */

// replace "=" of states for by
// it include set and get
// dont need "status.value" just "state"


/*
 * lazy
 */

    class Database {
        fun save() {}
    }

    class MyClass {
        // se inicializa hasta que se ocupe
        private val db by lazy { Database() }

        fun save() {
            db.save()
        }
    }

    val mC = MyClass()
    mC.save()


/*
 * Observable
 */

    class MyClassObs {
        var x: Int by Delegates.observable(5){ property, oldValue, newValue ->
            print("Old value: $oldValue, New Value: $newValue")
        }
    }

    val myObsClass = MyClassObs()
    myObsClass.x
    myObsClass.x = 10

/*
 * Observable
 */

    class MyClassVet {
        var x: Int by Delegates.vetoable(3){ property, oldValue, newValue ->
            print("Old value: $oldValue, New Value: $newValue")
            newValue > oldValue
        }
    }

    val myVetClass = MyClassVet()
    myVetClass.x
    myVetClass.x = 10
    myVetClass.x
    myVetClass.x = 7
    myVetClass.x

/*
 * Lateinit
 */

    lateinit var late: String
// Use delegates for nulls and types not ables for lateinit
    var lateDoub : Double by Delegates.notNull()

    fun printLate(){
//        if (::late.isInitialized){
//            print(late)
//        }
    }

    printLate()
    late = "Holis"
    printLate()



}