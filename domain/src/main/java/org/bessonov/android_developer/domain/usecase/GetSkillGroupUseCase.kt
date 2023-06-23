package org.bessonov.android_developer.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.bessonov.android_developer.domain.model.SkillGroup
import org.bessonov.android_developer.domain.repository.SkillGroupRepository

class GetSkillGroupUseCase(private val repository: SkillGroupRepository) {

    operator fun invoke(): Flow<List<SkillGroup>> {
        return repository.getList()
    }
}