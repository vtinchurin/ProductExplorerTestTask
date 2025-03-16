package ru.vtinchurin.productsexplorer.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface RunAsync {

    fun <T : Any> handleAsync(
        scope: CoroutineScope,
        heavy: suspend () -> T,
        ui: (T) -> Unit
    )

    class Base : RunAsync {
        override fun <T : Any> handleAsync(
            scope: CoroutineScope,
            heavy: suspend () -> T,
            ui: (T) -> Unit
        ) {
            scope.launch(Dispatchers.IO) {
                val result = heavy.invoke()
                withContext(Dispatchers.Main) {
                    ui(result)
                }
            }
        }
    }
}