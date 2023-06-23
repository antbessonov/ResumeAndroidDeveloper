package org.bessonov.android_developer.data.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.bessonov.android_developer.data.database.model.SkillGroupDbModel

@Dao
interface SkillGroupDao {

    @Query("SELECT * FROM skill_groups")
    fun getList(): Flow<List<SkillGroupDbModel>>
}