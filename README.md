# ZPSM---Jetpack-Multiplatform-Compose

## Zadanie: „Pokaż ukończone zadania”

### 1. **Rozszerz `TodoState`**

`TodoState.kt`:

``` kotlin
data class TodoState(
    val todos: List<TodoItem> = emptyList(),
    val showCompleted: Boolean = true
)
```

### 2. **Zmodyfikuj TodoViewModel – filtruj tutaj, nie w repo**

**Dodaj funkcję przełączającą**
``` kotlin
fun toggleShowCompleted() {
    _state.value = _state.value.copy(
        showCompleted = !_state.value.showCompleted
    )
    applyFilter()
}
```
**Dodaj funkcję filtrującą**
``` kotlin
private fun applyFilter() {
    val raw = repository.items.value

    val filtered = if (_state.value.showCompleted) {
        raw
    } else {
        raw.filter { !it.isDone }
    }

    _state.value = _state.value.copy(todos = filtered)
}

```
**Nasłuchuj zmian z repo** - zastosuj funkcję apply filters w miejscu operacji na zadaniach

### 3. **Dodaj przełącznik w UI**
W `App.kt` dodaj przezłącznik:
``` kotlin
Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier.padding(16.dp)
) {
    Text("Pokaż ukończone")
    Spacer(Modifier.weight(1f))
    Switch(
        checked = state.showCompleted,
        onCheckedChange = { viewModel.toggleShowCompleted() }
    )
}

```

### GOTOWE!
Masz w pełni działające zadanie oparte na Twojej aplikacji:
- UI + filtr,
- logika w ViewModelu,
- repozytorium nietknięte,
- reakcja na toggle i ukrywanie ukończonych zadań.
