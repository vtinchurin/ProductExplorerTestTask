package ru.vtinchurin.productsexplorer.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.vtinchurin.productsexplorer.domain.LoadResult
import ru.vtinchurin.productsexplorer.domain.model.Item

interface ItemRepository {

    fun getItems(query: String = ""):Flow<LoadResult<List<Item>>>

    suspend fun editAmount(itemId:Long, value: Int)

    suspend fun deleteItem(itemId: Long)
}