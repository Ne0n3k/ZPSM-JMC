package com.example.kmptodo.data

import com.example.kmptodo.domain.entity.TodoItem
import com.example.kmptodo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class InMemoryTodoRepository : TodoRepository {
    private val _items = MutableStateFlow<List<TodoItem>>(emptyList())
    override val items: StateFlow<List<TodoItem>> = _items

    private var nextId = 1L

    override fun add(text: String) {
        val t = text.trim()
        if (t.isEmpty()) return
        _items.value = _items.value + TodoItem(id = nextId++, text = t)
    }

    override fun toggle(id: Long) {
        _items.value = _items.value.map { if (it.id == id) it.copy(isDone = !it.isDone) else it }
    }

    override fun remove(id: Long) {
        _items.value = _items.value.filterNot { it.id == id }
    }
}
