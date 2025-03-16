package ru.vtinchurin.productsexplorer.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.vtinchurin.productsexplorer.R
import ru.vtinchurin.productsexplorer.presentation.MainViewModel

@Composable
fun MainScreen(viewModel : MainViewModel, modifier: Modifier = Modifier) {

    Scaffold(
        modifier = modifier, topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.BottomCenter
            ) {
                Spacer(modifier = Modifier.height(48.dp))
                Text(
                    stringResource(R.string.list_of_products),
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }) { innerPadding ->

        val state = viewModel.state.collectAsStateWithLifecycle()
        val customSaver = object : Saver<MutableState<TextFieldValue>, String> {

            override fun restore(value: String): MutableState<TextFieldValue> {
                return mutableStateOf(TextFieldValue(value))
            }

            override fun SaverScope.save(value: MutableState<TextFieldValue>): String {
                return value.value.text
            }

        }

        val inputState = rememberSaveable(saver = customSaver) { mutableStateOf(TextFieldValue("")) }
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 8.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            SearchView(state = inputState, modifier = Modifier.fillMaxWidth()) {
                viewModel.search(it.text)
            }
            Spacer(modifier = Modifier.height(16.dp))
            state.value.Show(onEdit = { id, amount ->
                viewModel.editItem(id, amount)
            }, onDelete = { id ->
                viewModel.deleteItem(id)
            })
        }
    }
}