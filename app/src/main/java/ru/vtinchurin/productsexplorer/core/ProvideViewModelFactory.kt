package ru.vtinchurin.productsexplorer.core

import androidx.lifecycle.ViewModelProvider

interface ProvideViewModelFactory {

    fun factory(): ViewModelProvider.Factory
}

