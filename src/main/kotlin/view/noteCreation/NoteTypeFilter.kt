package view.noteCreation

import data.notes.Note
import data.notes.NoteType

sealed interface NoteTypeFilter{
    class All : NoteTypeFilter {
        val tag = "All"
    }

    class General : NoteTypeFilter {
        val tag = "General"
    }

    class ToDo : NoteTypeFilter {
        val tag = "To do"
    }

    class Reminder : NoteTypeFilter {
        val tag = "Reminder"
    }

    class Event : NoteTypeFilter {
        val tag = "Event"
    }

    fun filter(list: List<Note>): List<Note> {
        return when (this) {
            is All -> list
            is Event -> list.filter { it.type == NoteType.EVENT }
            is General -> list.filter { it.type == NoteType.GENERAL }
            is Reminder -> list.filter { it.type == NoteType.REMINDER }
            is ToDo -> list.filter { it.type == NoteType.TODO }
        }
    }

    fun getNoteTypeFilterFromNoteTypeEnum(noteType: NoteType): NoteTypeFilter {
        return when (noteType) {
            NoteType.GENERAL -> General()
            NoteType.TODO -> ToDo()
            NoteType.REMINDER -> Reminder()
            NoteType.EVENT -> Event()
        }
    }
}
