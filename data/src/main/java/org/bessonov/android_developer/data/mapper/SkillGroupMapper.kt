package org.bessonov.android_developer.data.mapper

import org.bessonov.android_developer.data.database.model.SkillDbModel
import org.bessonov.android_developer.data.database.model.SkillGroupDbModel
import org.bessonov.android_developer.domain.model.SkillGroup

class SkillGroupMapper(
    private val skillMapper: SkillMapper
) {

    fun mapDbModelListToEntityList(
        skillGroupDbModelList: List<SkillGroupDbModel>,
        skillDbModelList: List<SkillDbModel>
    ): List<SkillGroup> {
        return skillGroupDbModelList.map { skillGroupDbModel ->
            SkillGroup(
                name = skillGroupDbModel.name,
                skillList = skillMapper.mapDbModelListToEntityList(
                    skillDbModelList = skillDbModelList,
                    skillGroupDbModel.name
                )
            )
        }
    }
}