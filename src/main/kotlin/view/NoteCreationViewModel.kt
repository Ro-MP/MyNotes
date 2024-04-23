import androidx.compose.runtime.snapshots.SnapshotStateList
import data.ListOfNotes
import data.remote.CoffeeAPI
import data.remote.CoffeeService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class NoteCreationViewModel {

    val coffeeService = CoffeeService()

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
    var coffees = SnapshotStateList<CoffeeAPI>()


    init {
        onSetButtonEnabled()
        getCoffees()
    }

    fun createNote() {
        _coroutineScope.launch {
            onNoteCreation()
            resetValues()

        }
    }

    fun cancelNote() {
        resetValues()
    }

    fun getCoffees() {
        _coroutineScope.launch {
            coffeeService.getHotCoffeeList()
                .collect {
                    coffees.addAll(it)
                }
        }
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


    private fun resetValues() {
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

