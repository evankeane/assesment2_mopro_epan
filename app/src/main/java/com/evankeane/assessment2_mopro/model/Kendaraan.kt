package com.evankeane.assessment2_mopro.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kendaraan")
data class Kendaraan(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,
    val merk : String,
    val warna: String,
    val tahun: String
)