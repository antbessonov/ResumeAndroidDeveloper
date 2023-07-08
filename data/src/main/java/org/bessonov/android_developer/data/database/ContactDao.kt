package org.bessonov.android_developer.data.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.bessonov.android_developer.data.database.model.ContactDbModel

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact.xml LIMIT 1")
    fun get(): Flow<ContactDbModel>
}