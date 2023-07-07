package org.bessonov.android_developer.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hard_skill_groups")
data class HardSkillGroupDbModel(
    @PrimaryKey
    @ColumnInfo(name = "name_hard_skill_group")
    val name: String
)