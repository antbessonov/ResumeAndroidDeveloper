package org.bessonov.android_developer.model.converter

import org.bessonov.android_developer.domain.model.HardSkill
import org.bessonov.android_developer.model.HardSkillUi
import org.bessonov.android_developer.model.StoredLogoSkillDrawable
import javax.inject.Inject

class HardSkillUiConverter @Inject constructor() {

    fun convertEntityListToUiModelList(entityList: List<HardSkill>): List<HardSkillUi> {
        return entityList.map { entity ->
            convertEntityToUiModel(entity = entity)
        }
    }

    private fun convertEntityToUiModel(entity: HardSkill): HardSkillUi {
        return HardSkillUi(
            name = convertNameToCapName(name = entity.name),
            logo = convertLogo(logo = entity.logo)
        )
    }

    private fun convertNameToCapName(name: String): String {
        return name
            .split(" ")
            .joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }
    }

    private fun convertLogo(logo: Int?): Int? {
        if (logo == null) {
            return null
        }
        return StoredLogoSkillDrawable.asRes(storedLogo = logo)
    }
}