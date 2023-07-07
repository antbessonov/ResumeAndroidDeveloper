package org.bessonov.android_developer.data.mapper

import org.bessonov.android_developer.data.database.model.HardSkillDbModel
import org.bessonov.android_developer.domain.model.HardSkill

class HardSkillMapper {

    fun mapDbModelListToEntityList(
        dbModelList: List<HardSkillDbModel>,
        name: String
    ): List<HardSkill> {
        return dbModelList
            .filter { dbModel ->
                dbModel.nameHardSkillGroup == name
            }
            .map { dbModel ->
                HardSkill(name = dbModel.name, logo = dbModel.logo)
            }
    }

    fun filterDbModelList(
        nameList: List<String>,
        dbModelList: List<HardSkillDbModel>
    ): List<HardSkillDbModel> {
        return dbModelList
            .filter { dbModel ->
                dbModel.name in nameList
            }
    }
}