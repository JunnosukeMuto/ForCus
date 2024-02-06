package com.jmuto.forcus.ui.scheduleedit

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jmuto.forcus.R
import com.jmuto.forcus.data.Schedule
import com.jmuto.forcus.ui.component.ForCusTimePicker
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ScheduleEdit(
    onDismissRequest: () -> Unit,
    viewModel: ScheduleEditViewModel = viewModel(factory = ScheduleEditViewModel.Factory)
) {
    // データベースへの非同期処理のためのコルーチン
    val coroutineScope = rememberCoroutineScope()

    // Schedule関連
    var selectedFeature by remember { mutableStateOf(Feature.APP_BLOCK)}
    var fromTime by remember { mutableStateOf(LocalTime.of(0, 0)) }
    var toTime by remember { mutableStateOf(LocalTime.of(0, 0)) }
//    var isRepeat by remember { mutableStateOf(false) }
    val selectedDays = remember { mutableStateMapOf(
        Day.SUN to false,
        Day.MON to false,
        Day.TUE to false,
        Day.WED to false,
        Day.THU to false,
        Day.FRI to false,
        Day.SAT to false,
    ) }

    // TimePicker関連
    var isFromTimePickerShown by remember { mutableStateOf(false) }
    var isToTimePickerShown by remember { mutableStateOf(false) }
    var timePickerState by remember { mutableStateOf(TimePickerState(0, 0, true)) }

    Column {
        // AppBlockかDetoxTimeを選ぶラジオボタン
        Text(text = stringResource(R.string.select_a_feature), style = MaterialTheme.typography.titleLarge)
        Column(Modifier.selectableGroup()) {
            Feature.entries.forEach { feature ->
                ListItem(
                    headlineContent = { Text(stringResource(feature.nameId)) },
                    supportingContent = { Text(stringResource(feature.descriptionId))},
                    leadingContent = { RadioButton(selected = feature == selectedFeature, onClick = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = feature == selectedFeature,
                            onClick = { selectedFeature = feature },
                            role = Role.RadioButton
                        )
                )
            }
        }
        // スケジュールを設定する
        Text(text = stringResource(id = R.string.edit_schedule), style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(top = 24.dp))
        // fromとto
        FlowRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            ListItem(
                headlineContent = { Text(fromTime.format(DateTimeFormatter.ofPattern("HH:mm"))) },
                leadingContent = { Text(stringResource(R.string.from), fontWeight = FontWeight.Bold) },
                modifier = Modifier
                    .weight(1F)
                    .clickable {
                        timePickerState = TimePickerState(fromTime.hour, fromTime.minute, true)
                        isFromTimePickerShown = true
                    }
            )
            ListItem(
                headlineContent = { Text(toTime.format(DateTimeFormatter.ofPattern("HH:mm"))) },
                leadingContent = { Text(stringResource(R.string.to), fontWeight = FontWeight.Bold) },
                modifier = Modifier
                    .weight(1F)
                    .clickable {
                        timePickerState = TimePickerState(toTime.hour, toTime.minute, true)
                        isToTimePickerShown = true
                    }
            )
        }
        // 曜日を選ぶ
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Day.entries.forEach { day ->
                IconToggleButton(
                    onCheckedChange = { selectedDays[day] = it },
                    checked = selectedDays[day] == true,
                    colors = IconButtonDefaults.iconToggleButtonColors(
                        checkedContentColor = MaterialTheme.colorScheme.primaryContainer,
                        checkedContainerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .padding(horizontal = 1.dp)
                ) {
                    Text(stringResource(day.shortDayId), fontWeight = FontWeight.Bold)
                }
            }
        }
        // 最下部のCancelとOK
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = onDismissRequest) {
                Text(stringResource(R.string.cancel), fontWeight = FontWeight.Bold)
            }
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveSchedule(
                            Schedule(
                                selectedFeature = selectedFeature,
                                fromTime = fromTime,
                                toTime = toTime,
                                sun = selectedDays[Day.SUN] == true,
                                mon = selectedDays[Day.MON] == true,
                                tue = selectedDays[Day.TUE] == true,
                                wed = selectedDays[Day.WED] == true,
                                thu = selectedDays[Day.THU] == true,
                                fri = selectedDays[Day.FRI] == true,
                                sat = selectedDays[Day.SAT] == true
                            )
                        )
                    }
                    onDismissRequest()
                }
            ) {
                Text(stringResource(R.string.ok), fontWeight = FontWeight.Bold)
            }
        }
    }
    ForCusTimePicker(
        timePickerState = timePickerState,
        isShown = isFromTimePickerShown,
        onCancel = { isFromTimePickerShown = false },
        onOK = {
            fromTime = LocalTime.of(timePickerState.hour, timePickerState.minute)
            isFromTimePickerShown = false
        }
    )
    ForCusTimePicker(
        timePickerState = timePickerState,
        isShown = isToTimePickerShown,
        onCancel = { isToTimePickerShown = false },
        onOK = {
            toTime = LocalTime.of(timePickerState.hour, timePickerState.minute)
            isToTimePickerShown = false
        }
    )
}