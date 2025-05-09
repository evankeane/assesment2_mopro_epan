package com.evankeane.assessment2_mopro.ui.theme.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evankeane.assessment2_mopro.database.KendaraanDao
import com.evankeane.assessment2_mopro.model.Kendaraan
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn


class MainViewModel(dao : KendaraanDao) : ViewModel() {

    val data : StateFlow<List<Kendaraan>> = dao.getAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )



}