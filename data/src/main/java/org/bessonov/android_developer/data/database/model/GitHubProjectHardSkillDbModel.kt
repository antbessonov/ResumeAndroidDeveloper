package org.bessonov.android_developer.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "github_project_skills",
    indices = [Index("name_github_project"), Index("name_hard_skill")],
    primaryKeys = ["name_github_project", "name_hard_skill"],
    foreignKeys = [ForeignKey(
        entity = GitHubProjectDbModel::class,
        parentColumns = arrayOf("name_github_project"),
        childColumns = arrayOf("name_github_project")
    ), ForeignKey(
        entity = HardSkillDbModel::class,
        parentColumns = arrayOf("name_hard_skill"),
        childColumns = arrayOf("name_hard_skill")
    )]
)
data class GitHubProjectHardSkillDbModel(
    @ColumnInfo(name = "name_github_project")
    val nameGitHubProject: String,
    @ColumnInfo(name = "name_hard_skill")
    val nameHardSkill: String
)
