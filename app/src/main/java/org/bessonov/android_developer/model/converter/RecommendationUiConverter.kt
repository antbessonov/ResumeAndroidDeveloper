package org.bessonov.android_developer.model.converter

import org.bessonov.android_developer.domain.model.Recommendation
import org.bessonov.android_developer.model.RecommendationUi
import org.bessonov.android_developer.model.StoredPhotoRecommenderDrawable
import javax.inject.Inject

class RecommendationUiConverter @Inject constructor() {

    fun convertEntityListToUiModelList(entityList: List<Recommendation>): List<RecommendationUi> {
        return entityList.map { entity ->
            convertEntityToUiModel(entity = entity)
        }
    }

    private fun convertEntityToUiModel(entity: Recommendation): RecommendationUi {
        return RecommendationUi(
            text = entity.text,
            nameRecommender = entity.nameRecommender,
            photoRecommenderResId = StoredPhotoRecommenderDrawable.asRes(storedPhoto = entity.photoRecommender),
            jobTitleRecommender = entity.jobTitleRecommender,
            nameCompanyRecommender = entity.nameCompanyRecommender
        )
    }
}