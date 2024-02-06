package com.jmuto.forcus.ui.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jmuto.forcus.MainApplication
import com.jmuto.forcus.data.Schedule
import com.jmuto.forcus.data.ScheduleRepository

class ScheduleCardViewModel(private val scheduleRepository: ScheduleRepository) : ViewModel() {
    suspend fun updateSchedule(schedule: Schedule) {
        scheduleRepository.updateSchedule(schedule)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MainApplication)
                val scheduleRepository = application.container.scheduleRepository
                ScheduleCardViewModel(scheduleRepository)
            }
        }
    }
}