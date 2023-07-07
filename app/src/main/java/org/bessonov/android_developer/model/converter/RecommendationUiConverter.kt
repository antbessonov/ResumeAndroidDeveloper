package org.bessonov.android_developer.model.converter

import org.bessonov.android_developer.domain.model.Recommendation
import org.bessonov.android_developer.model.RecommendationUi
import javax.inject.Inject

class RecommendationUiConverter @Inject constructor(
    private val recommenderUiConverter: RecommenderUiConverter
) {

    fun convertEntityListToUiModelList(entityList: List<Recommendation>): List<RecommendationUi> {
        return entityList.map { entity ->
            convertEntityToUiModel(entity = entity)
        }
    }

    private fun convertEntityToUiModel(entity: Recommendation): RecommendationUi {
        return RecommendationUi(
            text = entity.text,
            recommender = recommenderUiConverter.convertEntityToUiModel(entity = entity.recommender)
        )
    }
}