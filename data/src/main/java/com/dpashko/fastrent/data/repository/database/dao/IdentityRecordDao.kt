package com.dpashko.fastrent.data.repository.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dpashko.fastrent.data.repository.database.model.IdentityRecordEntity
import kotlinx.coroutines.flow.Flow

/**
 * Identity Record DAO that handles all the DB operations for [IdentityRecordEntity] table.
 */
@Dao
internal interface IdentityRecordDao {

    /**
     *  Adds or replaces entity in the table.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(entity: IdentityRecordEntity)

    /**
     * Gets the entity from the table by id, returns null if the entity does not exist.
     */
    @Query("SELECT * FROM identity_records WHERE id=:id")
    suspend fun get(id: String): IdentityRecordEntity?

    /**
     * Deletes the entity with specific id from the table.
     */
    @Query("DELETE FROM identity_records WHERE id=:id")
    suspend fun delete(id: String)

    /**
     * Removes all records from the table.
     */
    @Query("DELETE FROM identity_records")
    suspend fun clear()

    /**
     * Returns a hot [Flowable] that emits a specific entity starting from the current one and each
     * time the entity is added/updated/deleted.
     * [List] is used as a wrapper to emit an empty list if there is no current entity or upon
     * entity deletion.
     *
     * @see Query RxJava section.
     */
    @Query("SELECT * FROM identity_records WHERE id = :id")
    fun observe(id: String): Flow<List<IdentityRecordEntity>>
}