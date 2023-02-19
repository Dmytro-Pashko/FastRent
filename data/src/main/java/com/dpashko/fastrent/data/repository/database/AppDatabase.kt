package com.dpashko.fastrent.data.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dpashko.fastrent.data.repository.database.dao.IdentityRecordDao
import com.dpashko.fastrent.data.repository.database.model.IdentityRecordEntity

@Database(
    entities = [
        IdentityRecordEntity::class,
    ],
    version = 1,
    exportSchema = true
)
/**
 * Class specifies Room Database and provides DAO for each persistable model.
 */
abstract class AppDatabase : RoomDatabase() {

    abstract fun identityRecordDao(): IdentityRecordDao

    companion object {
        private const val DB_NAME = "app-data.db"

        fun getInstance(appContext: Context): AppDatabase {
            return Room.databaseBuilder(
                appContext,
                AppDatabase::class.java, DB_NAME
            ).build()
        }
    }
}
