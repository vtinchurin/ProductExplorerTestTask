package ru.vtinchurin.productsexplorer.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.vtinchurin.productsexplorer.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductCard(
    title: String,
    amount: String,
    date: String,
    tags: List<String> = emptyList(),
    modifier: Modifier = Modifier,
    onEdit: () -> Unit = {},
    onDelete: () -> Unit = {},
) {

    Card(
        modifier.heightIn(min = 125.dp, max = 250.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier.padding(8.dp)) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.titleLarge
                )
                IconButton(onClick = onEdit) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
            FlowRow(modifier = Modifier.fillMaxWidth()) {
                tags.forEach { tag ->
                    SuggestionChip(onClick = {}, label = {
                        Text(
                            text = tag,
                            maxLines = 1,
                        )
                    })
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
            Row(modifier = modifier.padding(top = 8.dp)) {
                Row(modifier = modifier, horizontalArrangement = Arrangement.Start) {
                    Box(modifier = Modifier.weight(1f)) {
                        Column {
                            Text(text = stringResource(R.string.in_stock), style = MaterialTheme.typography.labelMedium)
                            Text(amount)
                        }
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        Column {
                            Text(text = stringResource(R.string.add_date), style = MaterialTheme.typography.labelMedium)
                            Text(date)
                        }
                    }
                }
            }
        }
    }
}

