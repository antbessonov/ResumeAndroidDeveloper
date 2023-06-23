package org.bessonov.android_developer.data.mapper

import org.bessonov.android_developer.data.database.model.SkillDbModel
import org.bessonov.android_developer.domain.model.Skill

class SkillMapper {

    fun mapDbModelListToEntityList(
        skillDbModelList: List<SkillDbModel>,
        name: String
    ): List<Skill> {
        return skillDbModelList
            .filter { skillDbModel ->
                skillDbModel.nameSkillGroup == name
            }
            .map { skillDbModel ->
                Skill(name = skillDbModel.name, logo = skillDbModel.logo)
            }
    }
}