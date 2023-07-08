package org.bessonov.android_developer.data.mapper

import org.bessonov.android_developer.data.database.model.GitHubProjectDbModel
import org.bessonov.android_developer.data.database.model.GitHubProjectHardSkillDbModel
import org.bessonov.android_developer.data.database.model.HardSkillDbModel
import org.bessonov.android_developer.data.database.model.HardSkillGroupDbModel
import org.bessonov.android_developer.data.network.model.GitHubProjectDto
import org.bessonov.android_developer.domain.model.GitHubProject

class GitHubProjectMapper(
    private val gitHubProjectHardSkillMapper: GitHubProjectHardSkillMapper
) {

    fun mapDbModelListToEntityList(
        gitHubProjectDbModelList: List<GitHubProjectDbModel>,
        gitHubProjectHardSkillDbModelList: List<GitHubProjectHardSkillDbModel>,
        hardSkillDbModelList: List<HardSkillDbModel>,
        hardSkillGroupDbModelList: List<HardSkillGroupDbModel>
    ): List<GitHubProject> {
        return gitHubProjectDbModelList
            .map { dbModel ->
                mapDbModelToEntity(
                    dbModel = dbModel,
                    gitHubProjectHardSkillDbModelList = gitHubProjectHardSkillDbModelList,
                    hardSkillDbModelList = hardSkillDbModelList,
                    hardSkillGroupDbModelList = hardSkillGroupDbModelList
                )
            }
    }

    fun mapDtoListToDbModelList(dtoList: List<GitHubProjectDto>): List<GitHubProjectDbModel> {
        return dtoList
            .map { dto ->
                mapDtoToDbModel(dto = dto)
            }
    }

    private fun mapDbModelToEntity(
        dbModel: GitHubProjectDbModel,
        gitHubProjectHardSkillDbModelList: List<GitHubProjectHardSkillDbModel>,
        hardSkillDbModelList: List<HardSkillDbModel>,
        hardSkillGroupDbModelList: List<HardSkillGroupDbModel>
    ): GitHubProject {
        return GitHubProject(
            name = dbModel.name,
            description = dbModel.description,
            dateUpdate = dbModel.dateUpdate,
            hardSkillGroupList = gitHubProjectHardSkillMapper.mapDbModelListToEntityList(
                name = dbModel.name,
                dbModelList = gitHubProjectHardSkillDbModelList,
                hardSkillDbModelList = hardSkillDbModelList,
                hardSkillGroupDbModelList = hardSkillGroupDbModelList
            )
        )
    }

    private fun mapDtoToDbModel(dto: GitHubProjectDto): GitHubProjectDbModel {
        return GitHubProjectDbModel(
            name = dto.name,
            description = dto.description,
            dateUpdate = dto.dateUpdate,
        )
    }
}