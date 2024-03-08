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
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun NoteCreation() {

    val viewModel = NoteCreationViewModel()

    Column {
        with(viewModel){
            TextField(text =  title)
            TextField(text = description)
            Spacer(Modifier.height(8.dp))
            TypeDropdownMenu(type)

            Spacer(Modifier.height(8.dp))
            SetButtons(
                areButtonsEnable,
                { createNote() },
                { cancelNote() }
            )

            Spacer(Modifier.height(16.dp))
            ListOfNotes(notes)
        }
    }

}


@Composable
fun TextField(text: MutableState<String>){
    OutlinedTextField(
        value = text.value,
        onValueChange = { newText ->
            text.value = newText
        },
//        modifier = Modifier.keyInputFilter {
//            if (it.key.keyCode == 8) {
//                if (it.type == KeyEventType.KeyUp) text.value = text.value.dropLast(1)
//                true
//            } else {
//                false
//            }
//        }
    )
}



@Composable
fun TypeDropdownMenu(selectedType: MutableState<NoteType>){
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
                    selectedType.value = noteTypes[index]
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
    isButtonEnabled: MutableState<Boolean>,
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