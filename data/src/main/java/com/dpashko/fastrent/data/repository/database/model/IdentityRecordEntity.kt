package com.dpashko.fastrent.data.repository.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * The [IdentityRecordEntity] represents the database table schema for identity records.
 */
@Entity(tableName = "identity_records")
internal data class IdentityRecordEntity(

    /**
     * The unique key of the record.
     */
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    /**
     * The value of the record.
     */
    @ColumnInfo(name = "value")
    val value: String
)
