package ru.vtinchurin.productsexplorer.data.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TagDao {
    @Query(
        """select * 
        from tags 
        inner join tags_by_item on tags.id = tags_by_item.tag_id
        where tags_by_item.item_id =:itemId"""
    )
    suspend fun getTagsById(itemId: Long): List<TagCache>
}