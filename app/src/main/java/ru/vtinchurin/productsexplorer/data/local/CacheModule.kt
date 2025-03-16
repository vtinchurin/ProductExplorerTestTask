package ru.vtinchurin.productsexplorer.data.local

import android.content.Context
import androidx.room.Room
import ru.vtinchurin.productsexplorer.R


interface CacheModule {

    fun ItemDao(): ItemDao
    fun TagDao(): TagDao

    class Base(applicationContext: Context):CacheModule {

        private val database by lazy {
            Room.databaseBuilder(
                applicationContext,
                ItemDataBase::class.java,
                applicationContext.getString(R.string.app_name)
            ).createFromAsset("data.db").build()
        }


        override fun ItemDao() = database.itemDao()

        override fun TagDao() = database.tagDao()
    }
}