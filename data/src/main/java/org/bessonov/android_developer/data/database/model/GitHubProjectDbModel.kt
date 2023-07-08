package org.bessonov.android_developer.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "github_projects")
class GitHubProjectDbModel(
    @PrimaryKey
    @ColumnInfo(name = "name_github_project")
    val name: String,
    val description: String,
    @ColumnInfo(name = "date_update")
    val dateUpdate: String
)