package org.bessonov.android_developer.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "skill_groups")
data class SkillGroupDbModel(
    @PrimaryKey
    @ColumnInfo(name = "name_skill_group")
    val name: String
)