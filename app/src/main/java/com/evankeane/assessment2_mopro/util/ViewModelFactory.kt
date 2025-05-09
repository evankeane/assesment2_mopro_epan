package com.evankeane.assessment2_mopro.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.evankeane.assessment2_mopro.database.KendaraanDb
import com.evankeane.assessment2_mopro.ui.theme.screen.KendaraanViewModel
import com.evankeane.assessment2_mopro.ui.theme.screen.MainViewModel
import com.evankeane.assessment2_mopro.ui.theme.screen.RecycleViewModel

class ViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val db = KendaraanDb.getInstance(context)
        val kendaraanDao = db.dao
        val recycleDao = db.recycleDao

        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(kendaraanDao) as T
            }
            modelClass.isAssignableFrom(KendaraanViewModel::class.java) -> {
                KendaraanViewModel(kendaraanDao, recycleDao) as T
            }
            modelClass.isAssignableFrom(RecycleViewModel::class.java) -> {
                RecycleViewModel(recycleDao, kendaraanDao, KendaraanViewModel(kendaraanDao, recycleDao)) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}