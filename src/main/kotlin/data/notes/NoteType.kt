package data.notes

enum class NoteType(val tag: String) {
    GENERAL("General"),
    TODO("To do"),
    REMINDER("Reminder"),
    EVENT("Event")
}