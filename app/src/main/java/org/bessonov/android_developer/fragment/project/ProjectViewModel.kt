package org.bessonov.android_developer.fragment.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.bessonov.android_developer.domain.model.GitHubProject
import org.bessonov.android_developer.domain.usecase.GetGitHubProjectListUseCase
import org.bessonov.android_developer.domain.usecase.LoadGitHubProjectListUseCase
import org.bessonov.android_developer.domain.util.ErrorMessage
import org.bessonov.android_developer.domain.util.LoadingResult
import org.bessonov.android_developer.domain.util.OperationFailed
import org.bessonov.android_developer.model.converter.GitHubProjectUiConverter
import org.bessonov.android_developer.util.mutableStateIn
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    getGitHubProjectListUseCase: GetGitHubProjectListUseCase,
    private val loadGitHubProjectListUseCase: LoadGitHubProjectListUseCase,
    private val gitHubProjectUiConverter: GitHubProjectUiConverter
) : ViewModel() {

    private val loadingResult: MutableStateFlow<LoadingResult> =
        MutableStateFlow(LoadingResult.Loading)

    val uiState: StateFlow<ProjectState> = combine(
        getGitHubProjectListUseCase(),
        loadingResult
    ) { gitHubProjectList, loadingResult ->
        getProjectState(loadingResult, gitHubProjectList)
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
            initialValue = ProjectState.Loading
        )

    private val _errorMessage: MutableStateFlow<ErrorMessage?> = loadingResult
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
    val errorMessage = _errorMessage.asStateFlow()

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

    fun hideErrorMessage() {
        _errorMessage.update { null }
    }

    fun handleNavigationError() {
        _errorMessage.update { OperationFailed }
    }

    private fun getProjectState(
        loadingResult: LoadingResult,
        gitHubProjectList: List<GitHubProject>
    ): ProjectState {
        return when (loadingResult) {
            is LoadingResult.Loading -> ProjectState.Loading
            is LoadingResult.Error, is LoadingResult.Success -> ProjectState.Success(
                gitHubProjectList = gitHubProjectUiConverter.convertEntityListToUiModelList(
                    gitHubProjectList
                ),
                loadingResult = loadingResult
            )
        }
    }
}