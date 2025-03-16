package ru.vtinchurin.productsexplorer.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.vtinchurin.productsexplorer.presentation.composables.AmountEditDialog
import ru.vtinchurin.productsexplorer.presentation.composables.DeleteAlertDialog
import ru.vtinchurin.productsexplorer.presentation.composables.ProductCard
import ru.vtinchurin.productsexplorer.R

interface MainScreenState {

    @Composable
    fun Show(
        onEdit: (id: Long, amount: Int) -> Unit,
        onDelete: (id:Long)-> Unit
    )

    data class Content(
        private val content: List<ItemUi>,
    ) : MainScreenState {

        @Composable
        override fun Show(onEdit: (Long, Int) -> Unit, onDelete: (Long) -> Unit) {

            val deleteItemDialogIsOpen = rememberSaveable { mutableStateOf(false) }
            val editAmountDialogIsOpen = rememberSaveable { mutableStateOf(false) }
            val currentItemIdAndAmount = rememberSaveable { mutableStateOf(Pair(-1L, 0)) }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(content.size) {
                    val item = content[it]
                    item.Show { id, title, amount, date, tags ->
                        ProductCard(
                            title,
                            amount,
                            date,
                            tags,
                            modifier = Modifier.fillMaxWidth(),
                            onEdit = {
                                currentItemIdAndAmount.value = Pair(id, amount.toInt())
                                editAmountDialogIsOpen.value = true
                            },
                            onDelete = {
                                currentItemIdAndAmount.value = id to 0
                                deleteItemDialogIsOpen.value = true
                            })
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            if (deleteItemDialogIsOpen.value) {
                DeleteAlertDialog(
                    icon = Icons.Rounded.Warning,
                    title = stringResource(R.string.deleting),
                    description = stringResource(R.string.rusure)//"Вы действительно хотите удалить выбранный товар?",
                    ,onDismiss = { deleteItemDialogIsOpen.value = false },
                    onSuccess = {
                        onDelete(currentItemIdAndAmount.value.first)
                        deleteItemDialogIsOpen.value = false
                    })
            }

            if (editAmountDialogIsOpen.value) {
                AmountEditDialog(
                    value = currentItemIdAndAmount.value.second,
                    icon = Icons.Default.Settings,
                    title = stringResource(R.string.amount),
                    modifier = Modifier.fillMaxWidth(),
                    onCancel = {
                        editAmountDialogIsOpen.value = false
                    },
                    onSuccess = { newValue ->
                        editAmountDialogIsOpen.value = false
                        onEdit(currentItemIdAndAmount.value.first, newValue)
                    },
                )
            }
        }
    }

    data object Empty : MainScreenState {
        @Composable
        override fun Show(
            onEdit: (Long, Int) -> Unit,
            onDelete: (Long) -> Unit
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(stringResource(R.string.nothing_found))
            }
        }
    }
}