package org.bessonov.android_developer.model.converter

import org.bessonov.android_developer.domain.model.HardSkillGroup
import org.bessonov.android_developer.model.HardSkillGroupUi
import javax.inject.Inject

class HardSkillGroupUiConverter @Inject constructor(
    private val hardSkillUiConverter: HardSkillUiConverter
) {

    fun convertEntityListToUiModelList(entityList: List<HardSkillGroup>): List<HardSkillGroupUi> {
        return entityList.map { entity ->
            convertEntityToUiModel(entity = entity)
        }
    }

    private fun convertEntityToUiModel(entity: HardSkillGroup): HardSkillGroupUi {
        return HardSkillGroupUi(
            name = entity.name.replaceFirstChar(Char::uppercaseChar),
            hardSkillList = hardSkillUiConverter.convertEntityListToUiModelList(
                entityList = entity.hardSkillList
            )
        )
    }
}