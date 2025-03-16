package ru.vtinchurin.productsexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import ru.vtinchurin.productsexplorer.core.ProvideViewModelFactory
import ru.vtinchurin.productsexplorer.presentation.MainViewModel
import ru.vtinchurin.productsexplorer.presentation.composables.MainScreen
import ru.vtinchurin.productsexplorer.ui.theme.ProductsExplorerTheme

class MainActivity : ComponentActivity(), ProvideViewModelFactory {

    val viewModel: MainViewModel by viewModels {
        factory()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductsExplorerTheme {
                MainScreen(viewModel = viewModel, modifier = Modifier.fillMaxSize())
            }
        }
    }

    override fun factory() = (application as ProvideViewModelFactory).factory()
}
