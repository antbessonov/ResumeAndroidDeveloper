package org.bessonov.android_developer.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.bessonov.android_developer.domain.model.Recommendation
import org.bessonov.android_developer.domain.repository.RecommendationRepository

class GetRecommendationListUseCase(private val repository: RecommendationRepository) {

    operator fun invoke(): Flow<List<Recommendation>> {
        return repository.getList()
    }
}