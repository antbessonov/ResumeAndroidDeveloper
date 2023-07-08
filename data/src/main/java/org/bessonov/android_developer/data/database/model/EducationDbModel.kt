package org.bessonov.android_developer.data.database.model

import androidx.room.*

@Entity(
    tableName = "educations",
    indices = [Index("name_education_group")],
    foreignKeys = [ForeignKey(
        entity = EducationGroupDbModel::class,
        parentColumns = arrayOf("name_education_group"),
        childColumns = arrayOf("name_education_group")
    )]
)
data class EducationDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    @ColumnInfo(name = "name_education_group")
    val group: String,
    val degree: String?,
    val company: String,
    @ColumnInfo(name = "date_start")
    val dateStart: String?,
    @ColumnInfo(name = "date_end")
    val dateEnd: String
)
