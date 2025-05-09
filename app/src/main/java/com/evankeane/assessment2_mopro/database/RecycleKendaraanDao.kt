package com.evankeane.assessment2_mopro.database


import androidx.room.*
import com.evankeane.assessment2_mopro.model.RecycleKendaraan
import kotlinx.coroutines.flow.Flow

@Dao
interface RecycleKendaraanDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecycleKendaraan(kendaraan: RecycleKendaraan)

    @Query("SELECT * FROM recycle_kendaraan ORDER BY deletedAt DESC")
    fun getAllRecycleKendaraan(): Flow<List<RecycleKendaraan>>

    @Delete
    suspend fun deleteRecycleKendaraan(kendaraan: RecycleKendaraan)

    @Query("DELETE FROM recycle_kendaraan")
    suspend fun deleteAllRecycleKendaraan()
}

