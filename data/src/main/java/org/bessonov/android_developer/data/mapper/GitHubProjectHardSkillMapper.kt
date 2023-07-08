package org.bessonov.android_developer.data.mapper

import org.bessonov.android_developer.data.database.model.GitHubProjectHardSkillDbModel
import org.bessonov.android_developer.data.database.model.HardSkillDbModel
import org.bessonov.android_developer.data.database.model.HardSkillGroupDbModel
import org.bessonov.android_developer.data.network.model.GitHubProjectDto
import org.bessonov.android_developer.domain.model.HardSkillGroup

class GitHubProjectHardSkillMapper(
    private val hardSkillMapper: HardSkillMapper,
    private val hardSkillGroupMapper: HardSkillGroupMapper
) {

    fun mapDbModelListToEntityList(
        name: String,
        dbModelList: List<GitHubProjectHardSkillDbModel>,
        hardSkillDbModelList: List<HardSkillDbModel>,
        hardSkillGroupDbModelList: List<HardSkillGroupDbModel>
    ): List<HardSkillGroup> {
        val nameHardSkillList = mapDbModelListToNameHardSkillList(
            name = name, dbModelList = dbModelList
        )
        val hardSkillList = hardSkillMapper.filterDbModelList(
            nameList = nameHardSkillList,
            dbModelList = hardSkillDbModelList
        )
        return hardSkillGroupMapper.mapDbModelListToEntityList(
            hardSkillDbModelList = hardSkillList,
            hardSkillGroupDbModelList = hardSkillGroupMapper.filterDbModelList(
                hardSkillDbModelList = hardSkillList,
                dbModelList = hardSkillGroupDbModelList
            )
        )
    }

    fun mapGitHubProjectDtoListToDbModelList(
        gitHubProjectDtoList: List<GitHubProjectDto>
    ): List<GitHubProjectHardSkillDbModel> {
        val dbModelList = mutableListOf<GitHubProjectHardSkillDbModel>()
        gitHubProjectDtoList
            .forEach { gitHubProjectDto ->
                gitHubProjectDto.topics
                    .forEach { topic ->
                        dbModelList.add(
                            GitHubProjectHardSkillDbModel(
                                nameGitHubProject = gitHubProjectDto.name,
                                nameHardSkill = mapTopic(topic = topic)
                            )
                        )
                    }
            }
        return dbModelList
    }

    private fun mapDbModelListToNameHardSkillList(
        name: String,
        dbModelList: List<GitHubProjectHardSkillDbModel>,
    ): List<String> {
        return dbModelList
            .filter { dbModel ->
                dbModel.nameGitHubProject == name
            }
            .map { dbModel ->
                dbModel.nameHardSkill
            }
    }

    private fun mapTopic(topic: String): String {
        return topic.replace("-", " ")
    }
}