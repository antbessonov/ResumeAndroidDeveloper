package org.bessonov.android_developer.model.converter

import org.bessonov.android_developer.domain.model.SkillGroup
import org.bessonov.android_developer.model.SkillGroupUi
import javax.inject.Inject

class SkillGroupUiConverter @Inject constructor(
    private val skillUiConverter: SkillUiConverter
) {

    fun convertEntityListToUiModelList(entityList: List<SkillGroup>): List<SkillGroupUi> {
        return entityList.map { entity ->
            convertEntityToUiModel(entity = entity)
        }
    }

    private fun convertEntityToUiModel(entity: SkillGroup): SkillGroupUi {
        return SkillGroupUi(
            name = entity.name.replaceFirstChar(Char::uppercaseChar),
            skillList = skillUiConverter.convertEntityListToUiModelList(
                entityList = entity.skillList
            )
        )
    }
}