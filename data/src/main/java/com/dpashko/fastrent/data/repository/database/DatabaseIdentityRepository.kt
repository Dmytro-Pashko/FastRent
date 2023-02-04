package com.dpashko.fastrent.data.repository.database

import com.dpashko.fastrent.data.repository.database.dao.IdentityRecordDao
import com.dpashko.fastrent.data.repository.database.model.IdentityRecordEntity
import com.dpashko.fastrent.domain.repository.Identity
import com.dpashko.fastrent.domain.repository.IdentityRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Implementation of [IdentityRepository] that uses [AppDatabase] for persistent entity data.
 */
internal class DatabaseIdentityRepository @Inject constructor(
    private val gson: Gson,
    appDatabase: AppDatabase,
) : IdentityRepository {

    private val dao: IdentityRecordDao = appDatabase.identityRecordDao()

    override suspend fun <T : Any> add(value: T): T {
        val annotation = getIdentitySafely(value::class.java, "add")
        dao.add(IdentityRecordEntity(annotation.uuid, serialize(value)))
        return value
    }

    override suspend fun <T : Any> get(clazz: Class<T>): T? {
        val annotation = getIdentitySafely(clazz, "get")
        val entity = dao.get(annotation.uuid)
        return if (entity == null) {
            null
        } else {
            deserialize(entity.value, clazz)
        }
    }

    override suspend fun delete(clazz: Class<*>) {
        val annotation = getIdentitySafely(clazz::class.java, "delete")
        dao.delete(annotation.uuid)
    }

    override suspend fun clear() {
        dao.clear()
    }

    override fun <T : Any> observe(clazz: Class<T>): Flow<T> {
        val annotation = getIdentitySafely(clazz::class.java, "observe")
        return dao.observe(annotation.uuid)
            .distinctUntilChanged()
            .map { deserialize(it.last().value, clazz) }
    }

    private fun <T> serialize(value: T): String = gson.toJson(value)

    private fun <T> deserialize(value: String, clazz: Class<T>): T = gson.fromJson(value, clazz)
        ?: throw IllegalStateException("Deserialized value cannot be null.")

    private fun <T> getIdentitySafely(clazz: Class<T>, methodName: String): Identity {
        return clazz.getAnnotation(Identity::class.java)
            ?: throw IllegalArgumentException(
                "$methodName() @Identity annotation is missing for the: ${clazz.name}"
            )
    }
}
