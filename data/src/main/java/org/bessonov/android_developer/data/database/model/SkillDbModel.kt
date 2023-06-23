package org.bessonov.android_developer.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "skills",
    foreignKeys = [ForeignKey(
        entity = SkillGroupDbModel::class,
        parentColumns = arrayOf("name_skill_group"),
        childColumns = arrayOf("name_skill_group")
    )]
)
data class SkillDbModel(
    @PrimaryKey
    val name: String,
    val logo: Int?,
    @ColumnInfo(name = "name_skill_group")
    val nameSkillGroup: String
)
