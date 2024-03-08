package data

import Note
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.delay

object ListOfNotes {
    private val _listOfNotes = mutableStateListOf<Note>()

    suspend fun getList(): SnapshotStateList<Note> {
        delay(3000)
        return _listOfNotes
    }

    suspend fun addElement(note: Note){
        delay(1000)
        _listOfNotes.add(note)
    }

}