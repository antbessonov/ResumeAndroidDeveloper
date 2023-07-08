package org.bessonov.android_developer.model.converter

import org.bessonov.android_developer.domain.model.GitHubProject
import org.bessonov.android_developer.domain.model.HardSkillGroup
import org.bessonov.android_developer.model.GitHubProjectUi
import org.bessonov.android_developer.model.HardSkillUi
import javax.inject.Inject

class GitHubProjectUiConverter @Inject constructor(
    private val hardSkillGroupUiConverter: HardSkillGroupUiConverter
) {

    fun convertEntityListToUiModelList(entityList: List<GitHubProject>): List<GitHubProjectUi> {
        return entityList
            .map { entity ->
                convertEntityToUiModel(entity = entity)
            }
    }

    private fun convertEntityToUiModel(entity: GitHubProject): GitHubProjectUi {
        return GitHubProjectUi(
            name = entity.name,
            description = entity.description,
            dateCreation = entity.dateCreation,
            hardSkillList = convertHardSkillGroupList(list = entity.hardSkillGroupList)
        )
    }

    private fun convertHardSkillGroupList(list: List<HardSkillGroup>): List<HardSkillUi> {
        val hardSkillUiList = mutableListOf<HardSkillUi>()
        val hardSkillGroupUiList = hardSkillGroupUiConverter
            .convertEntityListToUiModelList(entityList = list)
        hardSkillGroupUiList
            .forEach { hardSkillGroupUi ->
                hardSkillGroupUi.hardSkillList
                    .forEach { hardSkillUi ->
                        hardSkillUiList.add(hardSkillUi)
                    }
            }
        return hardSkillUiList
    }
}