package com.dpashko.fastrent.domain.repository

import kotlinx.coroutines.flow.Flow
import java.util.*

/**
 * A repository to store unique objects. Classes of the objects stored in this repository should be
 * annotated with @Identity.
 */
interface IdentityRepository {

    /**
     * Adds or replaces object in the repository.
     * Class of the object should be annotated with @Identity.
     *
     * @param value A value to set.
     * @return The [T] source that emits the added value.
     */
    suspend fun <T : Any> add(value: T): T

    /**
     * Gets the object related to class type.
     * Class of the object should be annotated with @Identity.
     *
     * @param clazz The class of associated value to get.
     * @return The associated value [T] or null the value does not exist.
     */
    suspend fun <T : Any> get(clazz: Class<T>): T?

    /**
     * Deletes the related object from repository.
     * Class of the object should be annotated with @Identity.
     *
     * @param clazz The class of associated value to delete.
     * @return A [Completable] of the deletion process.
     */
    suspend fun delete(clazz: Class<*>)

    /**
     * Deletes all records from the repository.
     *
     */
    suspend fun clear()

    /**
     * Returns a [Flow] that emits an [Optional] starting from the current one and each
     * time the object related to the [clazz] is added/updated/deleted. [Optional] contains
     * a related object, or is empty if there is no related object in the repository.
     *
     * @param clazz The class of value.
     * @return A hot [Flow] that emits an [Optional] with the object related to the [clazz]
     * from the repository or an empty [Optional], starting from the current one and each time
     * the object is added/updated/deleted.
     */
    fun <T : Any> observe(clazz: Class<T>): Flow<T>
}
