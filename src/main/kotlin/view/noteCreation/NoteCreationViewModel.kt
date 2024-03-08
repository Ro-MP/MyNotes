import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import data.ListOfNotes
import kotlinx.coroutines.*
import view.noteCreation.NoteCreationState
import kotlin.properties.Delegates

class NoteCreationViewModel() {

    private val _state = NoteCreationState()
    private val _notesModel = ListOfNotes
    private val _coroutineScope = MainScope()
    var notes = SnapshotStateList<Note>()

    val title : MutableState<String>
        get() = _state.title
    val description : MutableState<String>
        get() = _state.message
    val type : MutableState<NoteType>
        get() = _state.type
    val areButtonsEnable: MutableState<Boolean>
        get() = _state.isButtonEnabled


    fun createNote() {
        _coroutineScope.launch {
            onNoteCreation()
            resetValues()
        }
        getNotes()
    }

    fun resetValues() {
        _state.resetValues()
    }

    fun cancelNote() {
        resetValues()
    }

    fun getNotes() {
        _coroutineScope.launch {
            notes = async{ onGettingNotes() }.await()
        }
    }

    private suspend fun onNoteCreation() = withContext(Dispatchers.IO){
        _notesModel.addElement(
            Note(title = _state.title.value,
                description = _state.message.value,
                type = _state.type.value
            )
        )
    }

    suspend fun onGettingNotes() = withContext(Dispatchers.IO){
        _notesModel.getList()
    }

    fun finish() {
        _coroutineScope.coroutineContext.job.cancel()
    }
}

