package org.bessonov.android_developer.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.bessonov.android_developer.data.database.RecommendationDao
import org.bessonov.android_developer.data.mapper.RecommendationMapper
import org.bessonov.android_developer.domain.model.Recommendation
import org.bessonov.android_developer.domain.repository.RecommendationRepository

class RecommendationRepositoryImpl(
    private val recommendationDao: RecommendationDao,
    private val recommendationMapper: RecommendationMapper
) : RecommendationRepository {

    override fun getList(): Flow<List<Recommendation>> {
        return recommendationDao.getList()
            .map { dbModelList ->
                dbModelList
                    .map { dbModel ->
                        recommendationMapper.mapDbModelToEntity(dbModel = dbModel)
                    }
            }
    }
}