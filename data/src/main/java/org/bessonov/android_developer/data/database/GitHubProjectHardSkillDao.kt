package org.bessonov.android_developer.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.bessonov.android_developer.data.database.model.GitHubProjectHardSkillDbModel

@Dao
interface GitHubProjectHardSkillDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(gitHubProjectHardSkillList: List<GitHubProjectHardSkillDbModel>)

    @Query("SELECT * FROM github_project_skills")
    fun getList(): Flow<List<GitHubProjectHardSkillDbModel>>
}