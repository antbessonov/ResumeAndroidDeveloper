package org.bessonov.android_developer.model.converter

import org.bessonov.android_developer.domain.model.Recommender
import org.bessonov.android_developer.model.RecommenderUi
import org.bessonov.android_developer.model.StoredPhotoRecommenderDrawable
import javax.inject.Inject

class RecommenderUiConverter @Inject constructor() {

    fun convertEntityToUiModel(entity: Recommender): RecommenderUi {
        return RecommenderUi(
            name = entity.name,
            photoResId = StoredPhotoRecommenderDrawable.asRes(storedPhoto = entity.photo),
            jobTitle = entity.jobTitle,
            nameCompany = entity.nameCompany
        )
    }
}