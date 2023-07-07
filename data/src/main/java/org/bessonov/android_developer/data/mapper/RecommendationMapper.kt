package org.bessonov.android_developer.data.mapper

import org.bessonov.android_developer.data.database.model.RecommendationDbModel
import org.bessonov.android_developer.domain.model.Recommendation
import org.bessonov.android_developer.domain.model.Recommender

class RecommendationMapper {

    fun mapDbModelToEntity(dbModel: RecommendationDbModel): Recommendation {
        return Recommendation(
            text = dbModel.text,
            recommender = Recommender(
                name = dbModel.nameRecommender,
                photo = dbModel.photoRecommender,
                jobTitle = dbModel.jobTitleRecommender,
                nameCompany = dbModel.nameCompanyRecommender
            )
        )
    }
}