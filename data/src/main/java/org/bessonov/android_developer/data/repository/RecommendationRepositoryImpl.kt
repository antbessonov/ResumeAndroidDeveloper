package org.bessonov.android_developer.data.repository

import org.bessonov.android_developer.data.database.RecommendationDao
import org.bessonov.android_developer.data.mapper.RecommendationMapper
import org.bessonov.android_developer.domain.model.Recommendation
import org.bessonov.android_developer.domain.repository.RecommendationRepository

class RecommendationRepositoryImpl(
    private val recommendationDao: RecommendationDao,
    private val recommendationMapper: RecommendationMapper
) : RecommendationRepository {

    override suspend fun getList(): List<Recommendation> {
        return recommendationDao.getList().map { dbModel ->
            recommendationMapper.mapDbModelToEntity(dbModel = dbModel)
        }
    }
}