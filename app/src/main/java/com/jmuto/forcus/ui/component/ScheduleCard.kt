package com.jmuto.forcus.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jmuto.forcus.R
import com.jmuto.forcus.data.Schedule
import com.jmuto.forcus.ui.scheduleedit.Day
import kotlinx.coroutines.launch

@Composable
fun ScheduleCard(
    schedule: Schedule,
    viewModel: ScheduleCardViewModel = viewModel(factory = ScheduleCardViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("${schedule.fromTime} - ${schedule.toTime}", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Row {
                    if (schedule.isEveryday()) {
                        Text(stringResource(R.string.everyday))
                    } else if (schedule.isOnce()) {
                        Text(stringResource(R.string.run_once))
                    } else {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            schedule.selectedDays().forEach { day ->
                                Text(stringResource(day.DayId))
                            }
                        }
                    }
                    Text(" | ")
                    Text(stringResource(schedule.selectedFeature.nameId))
                }
            }
            Switch(
                checked = schedule.isEnabled,
                onCheckedChange = {
                    coroutineScope.launch {
                        viewModel.updateSchedule(
                            Schedule(
                                id = schedule.id,
                                isEnabled = it,
                                selectedFeature = schedule.selectedFeature,
                                fromTime = schedule.fromTime,
                                toTime = schedule.toTime,
                                sun = schedule.sun,
                                mon = schedule.mon,
                                tue = schedule.tue,
                                wed = schedule.wed,
                                thu = schedule.thu,
                                fri = schedule.fri,
                                sat = schedule.sat
                            )
                        )
                    }
                }
            )
        }
    }
}