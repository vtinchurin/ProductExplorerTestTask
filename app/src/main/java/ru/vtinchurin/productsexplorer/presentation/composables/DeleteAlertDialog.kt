package ru.vtinchurin.productsexplorer.presentation.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.vtinchurin.productsexplorer.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun DeleteAlertDialog(
    icon: ImageVector,
    title: String,
    description: String,
    onDismiss: () -> Unit = {},
    onSuccess: () -> Unit = {}){
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(imageVector = icon, contentDescription = null)
        },
        title = {
            Text(title)
        },
        text = {
            Text(description)
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.no))
            }
        },
        confirmButton = {
            TextButton(onClick = onSuccess) {
                Text(stringResource(R.string.yes))
            }
        })
}

@Preview(showBackground = true)
@Composable
fun AlertDialogPreview() {
    DeleteAlertDialog(
        icon = Icons.Rounded.Warning,
        title = "Delete",
        description = "A you sure?",
        onDismiss = {},
        onSuccess = {})
}