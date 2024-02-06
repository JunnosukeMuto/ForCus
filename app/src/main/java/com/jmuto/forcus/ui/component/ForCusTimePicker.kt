package com.jmuto.forcus.ui.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jmuto.forcus.R
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForCusTimePicker(
    timePickerState: TimePickerState,
    isShown: Boolean,
    onCancel: () -> Unit,
    onOK: () -> Unit
) {
    if (isShown) {
        AlertDialog(
            onDismissRequest = onCancel,
            confirmButton = {
                TextButton(onClick = onOK) {
                    Text(stringResource(R.string.ok))
                } },
            dismissButton = {
                TextButton(onClick = onCancel) {
                    Text(stringResource(R.string.cancel))
                } },
            title = { Text(stringResource(R.string.select_time))},
            text = { TimePicker(state = timePickerState) }
        )
    }
}
