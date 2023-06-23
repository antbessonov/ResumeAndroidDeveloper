package org.bessonov.android_developer.model.converter

import org.bessonov.android_developer.domain.model.Skill
import org.bessonov.android_developer.model.SkillUi
import org.bessonov.android_developer.model.StoredLogoSkillDrawable
import javax.inject.Inject

class SkillUiConverter @Inject constructor() {

    fun convertEntityListToUiModelList(entityList: List<Skill>): List<SkillUi> {
        return entityList.map { entity ->
            convertEntityToUiModel(entity = entity)
        }
    }

    private fun convertEntityToUiModel(entity: Skill): SkillUi {
        return SkillUi(
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