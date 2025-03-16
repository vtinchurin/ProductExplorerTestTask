package ru.vtinchurin.productsexplorer.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

@Immutable
data class ItemUi(
    private val id: Long,
    private val title: String,
    private val amount: String,
    private val date: String,
    private val tags: List<String>,
) {

    @Composable
    fun Show(
        cardUi: @Composable (
            id: Long,
            title: String,
            amount: String,
            date: String,
            tags: List<String>,
        ) -> Unit
    ) {
        cardUi.invoke(id, title, amount, date, tags)
    }
}