package org.bessonov.android_developer.model

import androidx.annotation.DrawableRes

data class RecommenderUi(
    val name: String,
    @DrawableRes val photoResId: Int,
    val jobTitle: String,
    val nameCompany: String
)
