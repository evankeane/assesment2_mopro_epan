package com.evankeane.assessment2_mopro.ui.theme.screen


import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.evankeane.assessment2_mopro.R
import com.evankeane.assessment2_mopro.ui.theme.Assessment2_MoproTheme
@Composable
fun DisplayAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
){
    AlertDialog(
        text = { Text(text = stringResource(R.string.pesan_hapus)) },
        confirmButton ={
            TextButton(onClick = {onConfirmation()}) {
                Text(text = stringResource(R.string.tombol_hapus))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(text = stringResource(R.string.tombol_batal))
            }
        },
        onDismissRequest = { onDismissRequest() }
    )
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DialogPreview(){
    Assessment2_MoproTheme {
        DisplayAlertDialog(
            onDismissRequest = {},
            onConfirmation = {}
        )
    }
}