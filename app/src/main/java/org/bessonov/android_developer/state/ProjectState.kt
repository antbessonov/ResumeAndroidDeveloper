package org.bessonov.android_developer.state

import org.bessonov.android_developer.model.GitHubProjectUi

sealed class ProjectState {

    object Loading : ProjectState()

    data class Success(
        val gitHubProjectList: List<GitHubProjectUi>,
        val isLoadingResultReceived: Boolean?
    ) : ProjectState()
}
