package ru.vtinchurin.productsexplorer.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.vtinchurin.productsexplorer.domain.model.Item

@Entity(tableName = "items")
data class ItemCache(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("time")
    val time: Long,
    @ColumnInfo("amount")
    val amount: Int,
)


fun ItemCache.mapToItem(tags: List<String>) =
    Item(id = id, title = title, date = time, amount = amount, tags = tags)
