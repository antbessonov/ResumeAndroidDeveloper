package org.bessonov.android_developer.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.bessonov.android_developer.data.database.model.GitHubProjectDbModel

@Dao
interface GitHubProjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(gitHubProjectList: List<GitHubProjectDbModel>)

    @Query("SELECT * FROM github_projects")
    fun getList(): Flow<List<GitHubProjectDbModel>>
}