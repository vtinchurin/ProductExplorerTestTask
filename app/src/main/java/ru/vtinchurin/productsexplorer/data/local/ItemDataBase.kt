package ru.vtinchurin.productsexplorer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ItemCache::class, TagCache::class, TagByItemCache::class], version = 1)
abstract class ItemDataBase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    abstract fun tagDao(): TagDao
}