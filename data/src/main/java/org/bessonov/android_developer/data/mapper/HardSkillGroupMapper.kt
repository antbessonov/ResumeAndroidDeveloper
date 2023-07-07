package org.bessonov.android_developer.data.mapper

import org.bessonov.android_developer.data.database.model.HardSkillDbModel
import org.bessonov.android_developer.data.database.model.HardSkillGroupDbModel
import org.bessonov.android_developer.domain.model.HardSkillGroup

class HardSkillGroupMapper(
    private val hardSkillMapper: HardSkillMapper
) {

    fun mapDbModelListToEntityList(
        hardSkillGroupDbModelList: List<HardSkillGroupDbModel>,
        hardSkillDbModelList: List<HardSkillDbModel>
    ): List<HardSkillGroup> {
        return hardSkillGroupDbModelList
            .map { hardSkillGroupDbModel ->
                HardSkillGroup(
                    name = hardSkillGroupDbModel.name,
                    hardSkillList = hardSkillMapper.mapDbModelListToEntityList(
                        dbModelList = hardSkillDbModelList,
                        hardSkillGroupDbModel.name
                    )
                )
            }
    }

    fun filterDbModelList(
        dbModelList: List<HardSkillGroupDbModel>,
        hardSkillDbModelList: List<HardSkillDbModel>,
    ): List<HardSkillGroupDbModel> {
        val nameHardSkillGroupList = hardSkillDbModelList
            .map { hardSkillDbModel ->
                hardSkillDbModel.nameHardSkillGroup
            }
        return dbModelList
            .filter { dbModel ->
                dbModel.name in nameHardSkillGroupList
            }
    }
}