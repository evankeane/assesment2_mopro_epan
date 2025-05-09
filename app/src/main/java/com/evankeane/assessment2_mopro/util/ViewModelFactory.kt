package com.evankeane.assessment2_mopro.util


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.evankeane.assessment2_mopro.database.KendaraanDb
import com.evankeane.assessment2_mopro.ui.theme.screen.KendaraanViewModel

import com.evankeane.assessment2_mopro.ui.theme.screen.MainViewModel

class ViewModelFactory (
    private val context : Context
): ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass : Class<T>): T {
        val dao = KendaraanDb.getInstance(context).dao
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(dao) as T
        }else if(modelClass.isAssignableFrom(KendaraanViewModel::class.java)){
            return KendaraanViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}