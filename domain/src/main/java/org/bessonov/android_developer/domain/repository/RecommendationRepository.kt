package org.bessonov.android_developer.domain.repository

import org.bessonov.android_developer.domain.model.Recommendation

interface RecommendationRepository {

    suspend fun getList(): List<Recommendation>
}