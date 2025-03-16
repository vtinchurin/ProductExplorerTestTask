package ru.vtinchurin.productsexplorer.core

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import ru.vtinchurin.productsexplorer.data.local.CacheDataSource
import ru.vtinchurin.productsexplorer.data.local.CacheModule
import ru.vtinchurin.productsexplorer.data.repository.ItemRepositoryImpl
import ru.vtinchurin.productsexplorer.domain.repository.ItemRepository
import ru.vtinchurin.productsexplorer.presentation.MainViewModel
import ru.vtinchurin.productsexplorer.presentation.mapper.ScreenStateMapper

class Core(
    applicationContext: Context
) : ProvideViewModelFactory {

    private val cacheModule = CacheModule.Base(applicationContext)

    private val repository = ItemRepositoryImpl(
        CacheDataSource.Base(
            itemDao = cacheModule.ItemDao(), tagDao = cacheModule.TagDao()
        )
    )

    inner class Factory() : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            val savedState = extras.createSavedStateHandle()
            return MainViewModel(
                repository = repository,
                runAsync = RunAsync.Base(),
                mapper = ScreenStateMapper.Base(),
                savedStateHandle = savedState
            ) as T
        }
    }

    override fun factory(): ViewModelProvider.Factory =  Factory()
}

