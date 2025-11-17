package com.example.kmptodo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.kmptodo.platform.getPlatformName
import com.example.kmptodo.presentation.di.ServiceLocator
import com.example.kmptodo.presentation.vm.TodoViewModel

@Composable
fun App(viewModel: TodoViewModel = remember { ServiceLocator.provideTodoViewModel() }) {
    MaterialTheme {
        Surface(Modifier.fillMaxSize()) { TodoScreen(viewModel) }
    }
}

@Composable
private fun TodoScreen(vm: TodoViewModel) {
    val state by vm.state.collectAsState()

    Column(Modifier.padding(16.dp)) {
        Text("KmpTodo – ${getPlatformName()}", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = state.input,
                onValueChange = vm::onInputChanged,
                label = { Text("Nowe zadanie") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = vm::onAddClicked) { Text("Dodaj") }
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(state.items, key = { it.id }) { item ->
                Card {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            item.text,
                            modifier = Modifier.weight(1f),
                            textDecoration = if (item.isDone) TextDecoration.LineThrough else null
                        )
                        Column {
                            TextButton(onClick = { vm.onToggle(item.id) }) {
                                Text(if (item.isDone) "Cofnij" else "Zrobione")
                            }
                            TextButton(onClick = { vm.onRemove(item.id) }) {
                                Text("Usuń")
                            }
                        }
                    }
                }
            }
        }
    }
}
