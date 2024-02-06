package com.jmuto.forcus.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jmuto.forcus.MainApplication
import com.jmuto.forcus.data.Schedule
import com.jmuto.forcus.data.ScheduleRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class ScheduleUiState(
    val scheduleList: List<Schedule> = listOf()
)

class ScheduleViewModel(private val scheduleRepository: ScheduleRepository) : ViewModel() {
    val scheduleUiState: StateFlow<ScheduleUiState> =
        scheduleRepository.getAllScheduleStreams().map { ScheduleUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = ScheduleUiState()
            )

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MainApplication)
                val scheduleRepository = application.container.scheduleRepository
                ScheduleViewModel(scheduleRepository)
            }
        }
    }
}