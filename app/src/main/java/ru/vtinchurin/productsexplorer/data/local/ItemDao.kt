package ru.vtinchurin.productsexplorer.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("select * from items where title like '%'||:query||'%'")
    fun getItems(query: String =""):Flow<List<ItemCache>>

    @Query("update items set amount =:amount where id =:id")
    suspend fun updateAmount(id:Long, amount:Int)

    @Query("delete from items where id =:id")
    suspend fun deleteItem(id:Long)

}

