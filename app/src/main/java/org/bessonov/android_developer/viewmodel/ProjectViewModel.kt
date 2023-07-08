package org.bessonov.android_developer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.bessonov.android_developer.domain.usecase.GetGitHubProjectListUseCase
import org.bessonov.android_developer.domain.usecase.LoadGitHubProjectListUseCase
import org.bessonov.android_developer.domain.util.LoadingResult
import org.bessonov.android_developer.model.converter.GitHubProjectUiConverter
import org.bessonov.android_developer.state.ProjectState
import org.bessonov.android_developer.util.mutableStateIn
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    getGitHubProjectListUseCase: GetGitHubProjectListUseCase,
    private val loadGitHubProjectListUseCase: LoadGitHubProjectListUseCase,
    private val gitHubProjectUiConverter: GitHubProjectUiConverter
) : ViewModel() {

    private val loadingResult: MutableStateFlow<LoadingResult?> = MutableStateFlow(null)

    private val isLoadingResultReceived: Flow<Boolean?> = loadingResult
        .map { loadingResult ->
            isLoadingResultReceived(loadingResult = loadingResult)
        }

    val uiState: StateFlow<ProjectState> = combine(
        getGitHubProjectListUseCase(),
        isLoadingResultReceived
    ) { gitHubProjectList, isLoadingResultReceived ->
        ProjectState.Success(
            gitHubProjectList = gitHubProjectUiConverter.convertEntityListToUiModelList(gitHubProjectList),
            isLoadingResultReceived = isLoadingResultReceived
        )
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
            initialValue = ProjectState.Loading
        )

    val loadingError = loadingResult
        .filter { loadingResult ->
            loadingResult is LoadingResult.Error
        }
        .map { loadingResult ->
            (loadingResult as LoadingResult.Error).message
        }
        .mutableStateIn(
            scope = viewModelScope,
            initialValue = null
        )

    init {
        loadGitHubProjectList()
    }

    fun loadGitHubProjectList() {
        viewModelScope.launch {
            loadingResult.emit(value = LoadingResult.Loading)
            val result = loadGitHubProjectListUseCase()
            loadingResult.emit(value = result)
        }
    }

    fun hideLoadingError() {
        loadingError.update { null }
    }

    private fun isLoadingResultReceived(loadingResult: LoadingResult?): Boolean? {
        return when (loadingResult) {
            LoadingResult.Loading -> false
            LoadingResult.Success, is LoadingResult.Error -> true
            null -> null
        }
    }
}