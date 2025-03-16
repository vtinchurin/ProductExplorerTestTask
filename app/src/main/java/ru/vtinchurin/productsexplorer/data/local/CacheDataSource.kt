package ru.vtinchurin.productsexplorer.data.local

interface CacheDataSource: ItemDao, TagDao {

    class Base(
        private val itemDao: ItemDao,
        private val tagDao: TagDao,
    ) : CacheDataSource,
        ItemDao by itemDao,
        TagDao by tagDao
}