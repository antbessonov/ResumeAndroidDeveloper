package org.bessonov.android_developer.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recommendations")
data class RecommendationDbModel(
    val text: String,
    @PrimaryKey
    @ColumnInfo(name = "name_recommender")
    val nameRecommender: String,
    @ColumnInfo(name = "photo_recommender")
    val photoRecommender: Int,
    @ColumnInfo(name = "job_title_recommender")
    val jobTitleRecommender: String,
    @ColumnInfo(name = "name_company_recommender")
    val nameCompanyRecommender: String
)