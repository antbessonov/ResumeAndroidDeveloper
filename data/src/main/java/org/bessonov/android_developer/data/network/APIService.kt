package org.bessonov.android_developer.data.network

import org.bessonov.android_developer.data.network.model.GitHubProjectDto
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

    @GET("users/antbessonov/repos")
    suspend fun getGitHubProjectList(): Response<List<GitHubProjectDto>>
}