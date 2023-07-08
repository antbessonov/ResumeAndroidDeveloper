package org.bessonov.android_developer.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "education_groups")
data class EducationGroupDbModel(
    @PrimaryKey
    @ColumnInfo(name = "name_education_group")
    val name: String
)