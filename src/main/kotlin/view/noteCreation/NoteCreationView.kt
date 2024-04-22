package view.noteCreation

import Note
import NoteCreationViewModel
import NoteType
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.awt.Image


@Composable
fun NoteCreation() {

    val viewModel = NoteCreationViewModel()

    with(viewModel) {
        val areButtonsEnable = isButtonEnabled.collectAsState()
        val title = title.collectAsState()
        val message = message.collectAsState()
        val type  = type.collectAsState()
        val notesList = notes

        Column {
            TextField(
                text =  title,
                onTextChanged = ::changeTitle,
                maxLines = 1
            )
            TextField(
                text = message,
                onTextChanged = ::changeMessage
            )
            Spacer(Modifier.height(8.dp))
            TypeDropdownMenu(
                selectedType = type,
                onChangedType = ::changeType
            )

            Spacer(Modifier.height(8.dp))
            SetButtons(
                isButtonEnabled = areButtonsEnable,
                onCreateNote = ::createNote,
                onCancelNote = ::cancelNote
            )

            Spacer(Modifier.height(16.dp))
            ListOfNotes(notesList)
        }
    }


}


@Composable
fun TextField(
    text: State<String>,
    onTextChanged: (String) -> Unit,
    maxLines: Int = 3
){
    OutlinedTextField(
        value = text.value,
        onValueChange = { newText ->
            onTextChanged(newText)
        },
        maxLines = maxLines
    )
}



@Composable
fun TypeDropdownMenu(
    selectedType: State<NoteType>,
    onChangedType: (NoteType) -> Unit
){
    var isExpanded by remember { mutableStateOf(false) }
    val noteTypes = NoteType.values()

    Box(
        modifier = Modifier.wrapContentSize(Alignment.TopStart)
    ) {
        Text(
            noteTypes[selectedType.value.ordinal].toString(),
            modifier = Modifier.clickable(
                onClick = { isExpanded = true })
                .background(Color.LightGray)
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
//            toggle = {
//
//            }
        ) {
            noteTypes.forEachIndexed { index, noteType ->
                DropdownMenuItem(onClick = {
                    onChangedType(noteTypes[index])
                    isExpanded = false
                }) {
                    Text(text = noteType.toString() )
                }
            }
        }
    }
}

@Composable
fun SetButtons(
    isButtonEnabled: State<Boolean>,
    onCreateNote: () -> Unit,
    onCancelNote: () -> Unit
){
    Row {
        OutlinedButton(
            onClick = onCancelNote,
            enabled = isButtonEnabled.value
        ) {
            Text("Cancel")
        }
        Button(
            onClick = onCreateNote,
            enabled = isButtonEnabled.value
        ) {
            Text("Add Note")
        }
    }
}

@Composable
fun ListOfNotes(list: SnapshotStateList<Note>){
    LazyColumn{
        items(
            items = list,
            itemContent = { item ->
                Text(
                    text = item.title,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = item.type.toString()
                )
                Text(
                    text = item.description,
                    fontStyle = FontStyle.Italic
                )
                Spacer(Modifier.height(8.dp))
            }
        )

    }
}