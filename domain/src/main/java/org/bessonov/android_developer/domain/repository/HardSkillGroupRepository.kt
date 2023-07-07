package org.bessonov.android_developer.domain.repository

import kotlinx.coroutines.flow.Flow
import org.bessonov.android_developer.domain.model.HardSkillGroup

interface HardSkillGroupRepository {

    fun getList(): Flow<List<HardSkillGroup>>
}