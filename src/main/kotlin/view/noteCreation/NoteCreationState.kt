package view.noteCreation

import NoteType
import androidx.compose.runtime.*

class NoteCreationState {
    var title = mutableStateOf("")
    var type = mutableStateOf(NoteType.GENERAL)
    var message = mutableStateOf("")

    val isButtonEnabled: MutableState<Boolean>
        get() = mutableStateOf(title.value.isNotEmpty() && message.value.isNotEmpty())

    fun resetValues() {
        title.value = ""
        message.value = ""
        type.value = NoteType.GENERAL
    }
}