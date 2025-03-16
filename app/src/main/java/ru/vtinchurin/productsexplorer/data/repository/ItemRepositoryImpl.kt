package ru.vtinchurin.productsexplorer.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import ru.vtinchurin.productsexplorer.data.local.CacheDataSource
import ru.vtinchurin.productsexplorer.data.local.mapToItem
import ru.vtinchurin.productsexplorer.domain.LoadResult
import ru.vtinchurin.productsexplorer.domain.model.Item
import ru.vtinchurin.productsexplorer.domain.repository.ItemRepository

class ItemRepositoryImpl(
    private val cacheDataSource: CacheDataSource,
) : ItemRepository {
    override fun getItems(query: String): Flow<LoadResult<List<Item>>> = flow {
        cacheDataSource.getItems(query = query)
            .transform { data ->
                if (data.isEmpty()) {
                    emit(LoadResult.Empty())
                } else {
                    val newData = data.map { item ->
                        val tags = cacheDataSource.getTagsById(itemId = item.id).map { it.value }
                        item.mapToItem(tags)
                    }
                    emit(LoadResult.Success(newData))
                }
            }.collect { loadResult ->
                emit(loadResult)
            }
    }

    override suspend fun editAmount(itemId: Long, value: Int) {
        cacheDataSource.updateAmount(itemId, value)
    }

    override suspend fun deleteItem(itemId: Long) {
        cacheDataSource.deleteItem(itemId)
    }
}
