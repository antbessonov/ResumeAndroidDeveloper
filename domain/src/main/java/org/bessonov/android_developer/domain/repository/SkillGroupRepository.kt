package org.bessonov.android_developer.domain.repository

import kotlinx.coroutines.flow.Flow
import org.bessonov.android_developer.domain.model.SkillGroup

interface SkillGroupRepository {

    fun getList(): Flow<List<SkillGroup>>
}