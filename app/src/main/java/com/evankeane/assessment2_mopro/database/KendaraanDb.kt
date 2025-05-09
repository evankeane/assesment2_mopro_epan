package com.evankeane.assessment2_mopro.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.evankeane.assessment2_mopro.model.Kendaraan


@Database(entities = [Kendaraan::class], version = 1, exportSchema = false)
abstract class KendaraanDb : RoomDatabase() {
    abstract val dao: KendaraanDao

    companion object {
        @Volatile
        private var INSTANCE: KendaraanDb? = null

        fun getInstance(context: Context): KendaraanDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KendaraanDb::class.java,
                    "kendaraan_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}