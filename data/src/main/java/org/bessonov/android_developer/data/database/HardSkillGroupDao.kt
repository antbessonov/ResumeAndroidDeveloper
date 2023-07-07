package org.bessonov.android_developer.data.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.bessonov.android_developer.data.database.model.HardSkillGroupDbModel

@Dao
interface HardSkillGroupDao {

    @Query("SELECT * FROM hard_skill_groups")
    fun getList(): Flow<List<HardSkillGroupDbModel>>
}