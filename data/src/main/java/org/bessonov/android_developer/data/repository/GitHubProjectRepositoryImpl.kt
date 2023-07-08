package org.bessonov.android_developer.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.bessonov.android_developer.data.database.GitHubProjectDao
import org.bessonov.android_developer.data.database.GitHubProjectHardSkillDao
import org.bessonov.android_developer.data.database.HardSkillDao
import org.bessonov.android_developer.data.database.HardSkillGroupDao
import org.bessonov.android_developer.data.mapper.GitHubProjectHardSkillMapper
import org.bessonov.android_developer.data.mapper.GitHubProjectMapper
import org.bessonov.android_developer.data.mapper.LoadingResultMapper
import org.bessonov.android_developer.data.network.APIService
import org.bessonov.android_developer.data.network.model.GitHubProjectDto
import org.bessonov.android_developer.domain.model.GitHubProject
import org.bessonov.android_developer.domain.repository.GitHubProjectRepository
import org.bessonov.android_developer.domain.util.LoadingResult
import org.bessonov.android_developer.domain.util.NetworkProblem
import org.bessonov.android_developer.domain.util.SomethingWentWrong
import retrofit2.Response
import java.io.IOException

class GitHubProjectRepositoryImpl(
    private val apiService: APIService,
    private val loadingResultMapper: LoadingResultMapper,
    private val gitHubProjectDao: GitHubProjectDao,
    private val gitHubProjectMapper: GitHubProjectMapper,
    private val gitHubProjectHardSkillDao: GitHubProjectHardSkillDao,
    private val gitHubProjectHardSkillMapper: GitHubProjectHardSkillMapper,
    private val hardSkillDao: HardSkillDao,
    private val hardSkillGroupDao: HardSkillGroupDao
) : GitHubProjectRepository {

    override suspend fun loadList(): LoadingResult {
        return try {
            val response = apiService.getGitHubProjectList()
            val loadingResult = loadingResultMapper.mapResponseToState(response = response)
            insertList(response = response, loadingResult = loadingResult)
            return loadingResult
        } catch (e: IOException) {
            LoadingResult.Error(message = NetworkProblem)
        }
        catch (e: Exception) {
            LoadingResult.Error(message = SomethingWentWrong)
        }
    }

    override fun getList(): Flow<List<GitHubProject>> {
        return combine(
            gitHubProjectDao.getList(),
            gitHubProjectHardSkillDao.getList(),
            hardSkillDao.getList(),
            hardSkillGroupDao.getList()
        ) { gitHubProjectDbModelList,
            gitHubProjectHardSkillDbModelList,
            hardSkillDbModelList,
            hardSkillGroupDbModelList ->
            gitHubProjectMapper.mapDbModelListToEntityList(
                gitHubProjectDbModelList = gitHubProjectDbModelList,
                gitHubProjectHardSkillDbModelList = gitHubProjectHardSkillDbModelList,
                hardSkillDbModelList = hardSkillDbModelList,
                hardSkillGroupDbModelList = hardSkillGroupDbModelList
            )
        }
    }

    private suspend fun insertList(
        loadingResult: LoadingResult,
        response: Response<List<GitHubProjectDto>>
    ) {
        if (loadingResult is LoadingResult.Success) {
            val gitHubProjectDtoList = response.body() ?: throw Exception("Response body is null.")
            gitHubProjectDao.insertList(
                gitHubProjectList = gitHubProjectMapper.mapDtoListToDbModelList(
                    dtoList = gitHubProjectDtoList
                )
            )
            gitHubProjectHardSkillDao.insertList(
                gitHubProjectHardSkillList = gitHubProjectHardSkillMapper
                    .mapGitHubProjectDtoListToDbModelList(
                        gitHubProjectDtoList = gitHubProjectDtoList
                    )
            )
        }
    }
}