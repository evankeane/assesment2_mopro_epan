package com.evankeane.assessment2_mopro.ui.theme.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evankeane.assessment2_mopro.database.KendaraanDao
import com.evankeane.assessment2_mopro.model.Kendaraan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class KendaraanViewModel(private val dao : KendaraanDao) : ViewModel() {


    fun insert(merk : String, warna:String, tahun : String){
        val kendaraan = Kendaraan(
            merk = merk,
            warna = warna,
            tahun = tahun
        )

        viewModelScope.launch (Dispatchers.IO){
            dao.insert(kendaraan)
        }
    }

    suspend fun getKendaraan(id : Long):Kendaraan?{
        return dao.getKendaraanById(id)
    }

    fun update(id:Long, merk: String,warna: String, tahun: String){
        val kendaraan = Kendaraan(
            id = id,
            merk = merk,
            warna = warna,
            tahun = tahun
        )

        viewModelScope.launch(Dispatchers.IO){
            dao.update(kendaraan)
        }
    }

    fun delete(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }

}