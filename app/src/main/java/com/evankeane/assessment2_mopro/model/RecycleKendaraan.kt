package com.evankeane.assessment2_mopro.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recycle_kendaraan")
data class RecycleKendaraan(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val merk: String,
    val warna: String,
    val tahun: String,
    val deletedAt: Long = System.currentTimeMillis() // Menyimpan waktu penghapusan
)
