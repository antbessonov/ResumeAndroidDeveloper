package org.bessonov.android_developer.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class ContactDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "telegram_id")
    val telegramId: String,
    val mail: String,
    val tel: String,
    @ColumnInfo(name = "linkedin_id")
    val linkedinId: String,
    @ColumnInfo(name = "github_id")
    val githubId: String,
    val latitude: Double,
    val longitude: Double
)