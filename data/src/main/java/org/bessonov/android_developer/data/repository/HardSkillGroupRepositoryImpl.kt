package org.bessonov.android_developer.data.repository

import kotlinx.coroutines.flow.*
import org.bessonov.android_developer.data.database.HardSkillDao
import org.bessonov.android_developer.data.database.HardSkillGroupDao
import org.bessonov.android_developer.data.mapper.HardSkillGroupMapper
import org.bessonov.android_developer.domain.model.HardSkillGroup
import org.bessonov.android_developer.domain.repository.HardSkillGroupRepository

class HardSkillGroupRepositoryImpl(
    private val hardSkillGroupDao: HardSkillGroupDao,
    private val hardSkillDao: HardSkillDao,
    private val hardSkillGroupMapper: HardSkillGroupMapper,
) : HardSkillGroupRepository {

    override fun getList(): Flow<List<HardSkillGroup>> {
        return combine(
            hardSkillGroupDao.getList(),
            hardSkillDao.getList()
        ) { hardSkillGroupDbModelList, hardSkillDbModelList ->
            hardSkillGroupMapper.mapDbModelListToEntityList(
                hardSkillGroupDbModelList = hardSkillGroupDbModelList,
                hardSkillDbModelList = hardSkillDbModelList
            )
        }
    }
}