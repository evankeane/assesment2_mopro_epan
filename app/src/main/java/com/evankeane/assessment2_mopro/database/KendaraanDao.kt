package com.evankeane.assessment2_mopro.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.evankeane.assessment2_mopro.model.Kendaraan
import kotlinx.coroutines.flow.Flow

@Dao
interface KendaraanDao {
    @Query("SELECT * FROM kendaraan")
    fun getAll(): Flow<List<Kendaraan>>

    @Query("SELECT * FROM kendaraan WHERE id = :id")
    suspend fun getKendaraanById(id:Long):Kendaraan?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(kendaraan: Kendaraan)

    @Update
    suspend fun update(kendaraan: Kendaraan)

    @Query("DELETE FROM kendaraan WHERE id = :id")
    suspend fun deleteById(id:Long)
}