package ru.vtinchurin.productsexplorer.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "tags_by_item", primaryKeys = ["tag_id", "item_id"])
data class TagByItemCache(
    @ColumnInfo("tag_id")
    val tagId: Long,
    @ColumnInfo("item_id")
    val itemId: Long,
)