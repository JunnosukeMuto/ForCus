package com.jmuto.forcus.ui.scheduleedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jmuto.forcus.MainApplication
import com.jmuto.forcus.data.Schedule
import com.jmuto.forcus.data.ScheduleRepository

class ScheduleEditViewModel(private val scheduleRepository: ScheduleRepository) : ViewModel() {
    suspend fun saveSchedule(schedule: Schedule) {
        scheduleRepository.insertSchedule(schedule)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MainApplication)
                val scheduleRepository = application.container.scheduleRepository
                ScheduleEditViewModel(scheduleRepository)
            }
        }
    }
}