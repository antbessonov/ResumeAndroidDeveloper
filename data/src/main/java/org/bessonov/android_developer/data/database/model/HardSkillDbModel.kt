package org.bessonov.android_developer.data.database.model

import androidx.room.*

@Entity(
    tableName = "hard_skills",
    indices = [Index("name_hard_skill_group")],
    foreignKeys = [ForeignKey(
        entity = HardSkillGroupDbModel::class,
        parentColumns = arrayOf("name_hard_skill_group"),
        childColumns = arrayOf("name_hard_skill_group")
    )]
)
data class HardSkillDbModel(
    @PrimaryKey
    @ColumnInfo(name = "name_hard_skill")
    val name: String,
    val logo: Int?,
    @ColumnInfo(name = "name_hard_skill_group")
    val nameHardSkillGroup: String
)
