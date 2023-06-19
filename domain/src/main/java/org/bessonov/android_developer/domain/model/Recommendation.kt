package org.bessonov.android_developer.domain.model

data class Recommendation(
    val text: String,
    val nameRecommender: String,
    val photoRecommender: Int,
    val jobTitleRecommender: String,
    val nameCompanyRecommender: String
)