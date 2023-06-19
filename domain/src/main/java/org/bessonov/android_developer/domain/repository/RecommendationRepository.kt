package org.bessonov.android_developer.domain.repository

import kotlinx.coroutines.flow.Flow
import org.bessonov.android_developer.domain.model.Recommendation

interface RecommendationRepository {

    fun getList(): Flow<List<Recommendation>>
}