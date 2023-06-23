package org.bessonov.android_developer.data.repository

import kotlinx.coroutines.flow.*
import org.bessonov.android_developer.data.database.SkillDao
import org.bessonov.android_developer.data.database.SkillGroupDao
import org.bessonov.android_developer.data.mapper.SkillGroupMapper
import org.bessonov.android_developer.domain.model.SkillGroup
import org.bessonov.android_developer.domain.repository.SkillGroupRepository

class SkillGroupRepositoryImpl(
    private val skillGroupDao: SkillGroupDao,
    private val skillDao: SkillDao,
    private val skillGroupMapper: SkillGroupMapper,
) : SkillGroupRepository {

    override fun getList(): Flow<List<SkillGroup>> {
        return combine(
            skillGroupDao.getList(),
            skillDao.getList()
        ) { skillGroupDbModelList, skillDbModelList ->
            skillGroupMapper.mapDbModelListToEntityList(
                skillGroupDbModelList = skillGroupDbModelList,
                skillDbModelList = skillDbModelList
            )
        }
    }
}