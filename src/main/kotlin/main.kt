import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import view.noteCreation.App


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
