package org.bessonov.android_developer.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.bessonov.android_developer.domain.model.HardSkillGroup
import org.bessonov.android_developer.domain.repository.HardSkillGroupRepository

class GetHardSkillGroupListUseCase(private val repository: HardSkillGroupRepository) {

    operator fun invoke(): Flow<List<HardSkillGroup>> {
        return repository.getList()
    }
}