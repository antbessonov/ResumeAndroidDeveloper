package org.bessonov.android_developer.model

import androidx.annotation.DrawableRes

data class RecommendationUi(
    val text: String,
    val nameRecommender: String,
    @DrawableRes val photoRecommenderResId: Int,
    val jobTitleRecommender: String,
    val nameCompanyRecommender: String
)