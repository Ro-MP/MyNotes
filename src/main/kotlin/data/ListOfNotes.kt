package data

import Note
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow

object ListOfNotes {
    private val _listOfNotes = MutableStateFlow<MutableList<Note>>(mutableListOf())

    suspend fun getList(): MutableStateFlow<MutableList<Note>> {
        delay(3000)
        return _listOfNotes
    }

    suspend fun addElement(note: Note){
        delay(1000)
        _listOfNotes.value.add(note)
    }

}