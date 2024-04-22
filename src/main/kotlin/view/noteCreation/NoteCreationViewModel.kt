import androidx.compose.runtime.snapshots.SnapshotStateList
import data.ListOfNotes
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine

class NoteCreationViewModel {

    private val _notesModel = ListOfNotes
    private val _coroutineScope = MainScope()

    private var _title: MutableStateFlow<String> = MutableStateFlow("")
    val title: StateFlow<String>
        get() = _title

    private var _type: MutableStateFlow<NoteType> = MutableStateFlow(NoteType.GENERAL)
    val type: StateFlow<NoteType>
        get() = _type

    private var _message: MutableStateFlow<String> = MutableStateFlow("")
    val message: StateFlow<String>
        get() = _message

    private var _isButtonEnabled = MutableStateFlow(false)
    val isButtonEnabled: StateFlow<Boolean>
        get() = _isButtonEnabled

    var notes = SnapshotStateList<Note>()


    init {
        onSetButtonEnabled()
    }

    fun createNote() {
        _coroutineScope.launch {
            onNoteCreation()
            resetValues()
        }
//        getNotes()
    }

    fun cancelNote() {
        resetValues()
    }

    private fun onSetButtonEnabled() {
        _coroutineScope.launch{
            _title.combine(_message){ title, message ->
                title.isNotEmpty() && message.isNotEmpty()
            }.collect {
                _isButtonEnabled.value = it
            }
        }


    }


    fun resetValues() {
        _title.value = ""
        _message.value = ""
        _type.value = NoteType.GENERAL
    }

    fun changeTitle(title: String) {
        _title.value = title
    }

    fun changeMessage(message: String) {
        _message.value = message
    }

    fun changeType(type: NoteType) {
        _type.value = type
    }

    private fun getNotes() {
        _coroutineScope.launch {
            withContext(Dispatchers.Default) { onGettingNotes() }.collect {
                 notes.removeAll { true }
                notes.addAll(it)
            }
        }
    }

    private suspend fun onNoteCreation() = withContext(Dispatchers.IO){
        val note = Note(title = _title.value,
            description = _message.value,
            type = _type.value
        )
        launch {
            _notesModel.addElement(note)
        }
        notes.add(note)
    }
//
    private suspend fun onGettingNotes() = withContext(Dispatchers.IO){
        _notesModel.getList()
    }

    fun finish() {
        _coroutineScope.coroutineContext.job.cancel()
    }
}

