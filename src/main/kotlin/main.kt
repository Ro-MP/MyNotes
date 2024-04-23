import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import view.noteCreation.NoteCreation


fun main() = application {

    Window(onCloseRequest = ::exitApplication) {
        DesktopMaterialTheme {
            NoteCreation()
        }
    }
}
