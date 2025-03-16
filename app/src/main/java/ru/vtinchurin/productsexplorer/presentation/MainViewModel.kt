package ru.vtinchurin.productsexplorer.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.vtinchurin.productsexplorer.core.RunAsync
import ru.vtinchurin.productsexplorer.domain.repository.ItemRepository
import ru.vtinchurin.productsexplorer.presentation.mapper.ScreenStateMapper

data class MainViewModel(
    private val repository: ItemRepository,
    private val runAsync: RunAsync,
    private val mapper: ScreenStateMapper,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    val lastSearch = savedStateHandle.getStateFlow(KEY, "")

    @OptIn(ExperimentalCoroutinesApi::class)
    val state = lastSearch.flatMapLatest { query ->
        repository.getItems(query)
            .map { result -> result.map(mapper) }
    }.stateIn(scope, started = SharingStarted.Lazily, initialValue = MainScreenState.Empty)


    fun editItem(id: Long, value: Int) {
        runAsync.handleAsync(scope, {
            repository.editAmount(id, value)
        }) {}
    }

    fun deleteItem(id: Long) {
        runAsync.handleAsync(scope, {
            repository.deleteItem(id)
        }) {}
    }

    fun search(query: String) {
        savedStateHandle[KEY] = query
    }

    companion object {
        private const val KEY = "QUERY"
    }
}
