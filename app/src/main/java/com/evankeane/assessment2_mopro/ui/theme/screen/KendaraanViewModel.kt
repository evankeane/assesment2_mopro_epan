package com.evankeane.assessment2_mopro.ui.theme.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evankeane.assessment2_mopro.database.KendaraanDao
import com.evankeane.assessment2_mopro.database.RecycleDao
import com.evankeane.assessment2_mopro.model.Kendaraan
import com.evankeane.assessment2_mopro.model.RecycleKendaraan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch



class KendaraanViewModel(
    private val dao: KendaraanDao,
    private val recycleDao: RecycleDao,
) : ViewModel() {


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

    fun softDeleteBarang(id: Long) = viewModelScope.launch(Dispatchers.IO) {
        // 1. Get the kendaraan first
        val kendaraan = dao.getKendaraanById(id) ?: return@launch

        // 2. Move to recycle bin
        val recycleKendaraan = RecycleKendaraan(
            originalId = kendaraan.id,
            merk = kendaraan.merk,
            warna = kendaraan.warna,
            tahun = kendaraan.tahun
        )
        recycleDao.insert(recycleKendaraan)

        // 3. Delete from main table
        dao.deleteById(id)
    }

    fun getAllRecycleBarang(): Flow<List<RecycleKendaraan>> {
        return recycleDao.getAllDeleted()
    }

}