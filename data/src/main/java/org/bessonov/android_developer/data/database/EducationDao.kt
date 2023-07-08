package org.bessonov.android_developer.data.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.bessonov.android_developer.data.database.model.EducationDbModel

@Dao
interface EducationDao {

    @Query("SELECT * FROM educations")
    fun getList(): Flow<List<EducationDbModel>>
}