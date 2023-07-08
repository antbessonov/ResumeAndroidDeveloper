package org.bessonov.android_developer.data.mapper

import org.bessonov.android_developer.data.network.model.GitHubProjectDto
import org.bessonov.android_developer.domain.util.LoadingResult
import org.bessonov.android_developer.domain.util.SomethingWentWrong
import retrofit2.Response

class LoadingResultMapper {

    fun mapResponseToState(response: Response<List<GitHubProjectDto>>): LoadingResult {
        return when (response.code()) {
            200 -> LoadingResult.Success
            else -> LoadingResult.Error(message = SomethingWentWrong)
        }
    }
}