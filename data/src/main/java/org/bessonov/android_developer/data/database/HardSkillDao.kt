package org.bessonov.android_developer.data.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.bessonov.android_developer.data.database.model.HardSkillDbModel

@Dao
interface HardSkillDao {

    @Query("SELECT * FROM hard_skills")
    fun getList(): Flow<List<HardSkillDbModel>>
}