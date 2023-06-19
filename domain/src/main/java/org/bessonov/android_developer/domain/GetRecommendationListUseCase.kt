package org.bessonov.android_developer.domain

import org.bessonov.android_developer.domain.model.Recommendation
import org.bessonov.android_developer.domain.repository.RecommendationRepository

class GetRecommendationListUseCase(private val repository: RecommendationRepository) {

    suspend operator fun invoke(): List<Recommendation> {
        return repository.getList()
    }
}