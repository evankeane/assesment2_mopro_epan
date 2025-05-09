package com.evankeane.assessment2_mopro.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evankeane.assessment2_mopro.model.RecycleKendaraan
import kotlinx.coroutines.flow.Flow

@Dao
interface RecycleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recycleKendaraan: RecycleKendaraan)

    @Query("DELETE FROM recycle WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("SELECT * FROM recycle WHERE id = :id")
    suspend fun getById(id: Long): RecycleKendaraan?

    @Query("SELECT * FROM recycle ORDER BY deletedAt DESC")
    fun getAllDeleted(): Flow<List<RecycleKendaraan>>

    @Query("SELECT * FROM recycle WHERE id = :id")
    suspend fun getRecycleBarangById(id: Int): RecycleKendaraan?
}