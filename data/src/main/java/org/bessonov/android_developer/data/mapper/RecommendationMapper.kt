package org.bessonov.android_developer.data.mapper

import org.bessonov.android_developer.data.database.model.RecommendationDbModel
import org.bessonov.android_developer.domain.model.Recommendation

class RecommendationMapper {

    fun mapDbModelToEntity(dbModel: RecommendationDbModel): Recommendation {
        return Recommendation(
            text = dbModel.text,
            nameRecommender = dbModel.nameRecommender,
            photoRecommender = dbModel.photoRecommender,
            jobTitleRecommender = dbModel.jobTitleRecommender,
            nameCompanyRecommender = dbModel.nameCompanyRecommender
        )
    }
}