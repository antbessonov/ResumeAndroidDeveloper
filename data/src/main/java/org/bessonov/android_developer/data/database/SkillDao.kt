package org.bessonov.android_developer.data.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.bessonov.android_developer.data.database.model.SkillDbModel

@Dao
interface SkillDao {

    @Query("SELECT * FROM skills")
    fun getList(): Flow<List<SkillDbModel>>
}