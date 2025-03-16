package ru.vtinchurin.productsexplorer

import android.app.Application
import ru.vtinchurin.productsexplorer.core.Core
import ru.vtinchurin.productsexplorer.core.ProvideViewModelFactory

class App : Application(), ProvideViewModelFactory {

    private val core: Core by lazy {
        Core(applicationContext)
    }

    override fun factory() = core.factory()
}