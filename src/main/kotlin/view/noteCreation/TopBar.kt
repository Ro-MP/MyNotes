package view.noteCreation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.List
import androidx.compose.runtime.*
import data.notes.NoteType

@Composable
fun TopBar(typeFilter: MutableState<NoteTypeFilter>) {
    TopAppBar(
        title = { Text("My Notes") },
        actions = {
            var isMenuExpanded by remember { mutableStateOf(false) }
            IconButton(
                onClick = { isMenuExpanded = !isMenuExpanded }
            ) {
                Icon(
                    imageVector = Icons.Rounded.List,
                    contentDescription = "Filter"
                )
                DropdownMenu(
                    expanded = isMenuExpanded,
                    onDismissRequest = { isMenuExpanded = false }
                ) {
                    DropdownMenuItem(onClick = {
                        isMenuExpanded = false
                        typeFilter.value = NoteTypeFilter.All()
                    }) {
                        Text("ALL")
                    }
                    NoteType.values().forEach { noteType ->
                        DropdownMenuItem(onClick = {
                            isMenuExpanded = false

                            typeFilter.value = typeFilter.value.getNoteTypeFilterFromNoteTypeEnum(noteType)
                        }) {
                            Text(noteType.name)
                        }
                    }
                }
            }
        }
    )
}

