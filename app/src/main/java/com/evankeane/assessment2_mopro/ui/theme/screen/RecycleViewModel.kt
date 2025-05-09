package com.evankeane.assessment2_mopro.ui.theme.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evankeane.assessment2_mopro.database.KendaraanDao
import com.evankeane.assessment2_mopro.database.RecycleDao
import com.evankeane.assessment2_mopro.model.Kendaraan
import com.evankeane.assessment2_mopro.model.RecycleKendaraan
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
class RecycleViewModel(
    private val recycleDao: RecycleDao,
    private val kendaraanDao: KendaraanDao,
    private val repository: KendaraanViewModel

    ) : ViewModel() {


//    fun deleteWithUndo(id: Long) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val kendaraan = kendaraanDao.getKendaraanById(id) ?: return@launch
//            val recycle = RecycleKendaraan(
//                originalId = kendaraan.id,
//                merk = kendaraan.merk,
//                warna = kendaraan.warna,
//                tahun = kendaraan.tahun,
//                deletedAt = System.currentTimeMillis()
//            )
//            recycleDao.insert(recycle)
//            kendaraanDao.deleteById(id)
//        }
//    }

    val deletedItems: StateFlow<List<RecycleKendaraan>> = recycleDao.getAllDeleted()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Restore from Recycle Bin
    fun restoreKendaraan(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val recycle = recycleDao.getById(id) ?: return@launch
            val kendaraan = Kendaraan(
                id = recycle.originalId,
                merk = recycle.merk,
                warna = recycle.warna,
                tahun = recycle.tahun,
                createdAt = System.currentTimeMillis()
            )
            kendaraanDao.insert(kendaraan)
            recycleDao.deleteById(id)
        }
    }

//    suspend fun insertToRecycle(kendaraan: Kendaraan) {
//        kendaraanDao.insert(kendaraan)
//    }

    // Permanent delete from Recycle Bin
    fun permanentDelete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            recycleDao.deleteById(id)
        }
    }

//    val allRecycleBarang: Flow<List<RecycleKendaraan>> = repository.getAllRecycleBarang()
    }