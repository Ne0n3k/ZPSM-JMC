package com.example.kmptodo.presentation.vm

import com.example.kmptodo.domain.repository.TodoRepository
import com.example.kmptodo.domain.usecase.AddTodo
import com.example.kmptodo.domain.usecase.RemoveTodo
import com.example.kmptodo.domain.usecase.ToggleTodo
import com.example.kmptodo.presentation.state.TodoState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TodoViewModel(
    repo: TodoRepository,
    private val addTodo: AddTodo,
    private val toggleTodo: ToggleTodo,
    private val removeTodo: RemoveTodo
) {
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + job)

    private val _state = MutableStateFlow(TodoState())
    val state: StateFlow<TodoState> = _state

    init {
        scope.launch {
            repo.items.collectLatest { items ->
                _state.value = _state.value.copy(items = items)
            }
        }
    }

    fun onInputChanged(text: String) {
        _state.value = _state.value.copy(input = text)
    }

    fun onAddClicked() {
        addTodo(_state.value.input)
        _state.value = _state.value.copy(input = "")
    }

    fun onToggle(id: Long) = toggleTodo(id)
    fun onRemove(id: Long) = removeTodo(id)

    fun clear() = scope.cancel()
}
