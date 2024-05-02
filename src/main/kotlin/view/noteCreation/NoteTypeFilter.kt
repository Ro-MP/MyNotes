package view.noteCreation

import data.notes.Note
import data.notes.NoteType

sealed interface NoteTypeFilter {

    fun filter(list: List<Note>): List<Note>


    object All : NoteTypeFilter {
        override fun filter(list: List<Note>): List<Note> {
            return list
        }
    }

    class ByType(private val noteType: NoteType) : NoteTypeFilter {

        override fun filter(list: List<Note>): List<Note> {
            return when (noteType) {
                NoteType.EVENT -> list.filter { it.type == NoteType.EVENT }
                NoteType.GENERAL -> list.filter { it.type == NoteType.GENERAL }
                NoteType.TODO -> list.filter { it.type == NoteType.TODO }
                NoteType.REMINDER -> list.filter { it.type == NoteType.REMINDER }
            }
        }
    }

}


