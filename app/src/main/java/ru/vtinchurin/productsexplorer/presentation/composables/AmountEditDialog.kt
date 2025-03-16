package ru.vtinchurin.productsexplorer.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.vtinchurin.productsexplorer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmountEditDialog(
    value: Int,
    icon: ImageVector,
    title: String,
    modifier: Modifier = Modifier,
    onSuccess: (Int) -> Unit = {},
    onCancel: () -> Unit = {},
) {
    val amount = rememberSaveable { mutableIntStateOf(value) }
    BasicAlertDialog(onDismissRequest = {
        onCancel()
    }) {
        Card(
            modifier = modifier,
        ) {
            Column(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Icon(imageVector = icon, contentDescription = null)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Counter(value = amount.intValue, modifier = Modifier.fillMaxWidth()) {
                    amount.update(it)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = onCancel) {
                        Text(stringResource(id = R.string.cancel))
                    }
                    TextButton(onClick = { onSuccess(amount.intValue) }) {
                        Text(stringResource(id = R.string.accept))
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


@Composable
fun Counter(value: Int, modifier: Modifier = Modifier, onClick: (Int) -> Unit = {}) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onClick(-1) }) {
            Icon(painter = painterResource(R.drawable.ic_remove_circle), contentDescription = null)
        }
        Text(
            value.toString(),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
        IconButton(onClick = { onClick(1) }) {
            Icon(painter = painterResource(R.drawable.ic_add_circle), contentDescription = null)
        }
    }
}

fun MutableIntState.update(value: Int) {
    val block = { intValue = intValue + value }
    if (value > 0) {
        block.invoke()
    } else if (intValue > 0) {
        block.invoke()
    }
}