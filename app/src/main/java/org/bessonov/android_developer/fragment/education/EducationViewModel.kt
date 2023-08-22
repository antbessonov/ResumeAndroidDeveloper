package org.bessonov.android_developer.fragment.education

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import org.bessonov.android_developer.data.repository.EducationRepositoryImpl
import org.bessonov.android_developer.domain.model.Education
import org.bessonov.android_developer.domain.model.EducationGroup
import org.bessonov.android_developer.domain.usecase.GetEducationListUseCase
import org.bessonov.android_developer.model.converter.EducationUiConverter
import javax.inject.Inject

@HiltViewModel
class EducationViewModel @Inject constructor(
    private val educationRepositoryImpl: EducationRepositoryImpl,
    private val getEducationListUseCase: GetEducationListUseCase,
    private val educationUiConverter: EducationUiConverter
) : ViewModel() {

    private val group: MutableStateFlow<EducationGroup?> = MutableStateFlow(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val educationList: Flow<List<Education>> = group
        .flatMapLatest { group ->
            getEducationList(group = group)
        }

    val uiState: StateFlow<EducationState> =
        combine(group, educationList) { group, educationList ->
            EducationState.Success(
                group = group,
                educationList = educationUiConverter
                    .convertEntityListToUiModelList(entityList = educationList)
            )
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
                initialValue = EducationState.Loading
            )

    fun updateGroup(group: EducationGroup? = null) {
        this.group.update { group }
    }

    private fun getEducationList(group: EducationGroup? = null): Flow<List<Education>> {
        return educationRepositoryImpl.getList()
            .map { educationList ->
                getEducationListUseCase(educationList = educationList, group = group)
            }
    }
}