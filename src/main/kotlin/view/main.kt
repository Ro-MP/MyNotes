import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import view.noteCreation.NoteCreation


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        MaterialTheme {
            NoteCreation()
        }
    }
}
