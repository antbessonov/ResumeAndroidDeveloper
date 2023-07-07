package org.bessonov.android_developer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import org.bessonov.android_developer.domain.usecase.GetContactUseCase
import org.bessonov.android_developer.domain.usecase.GetRecommendationListUseCase
import org.bessonov.android_developer.domain.usecase.GetHardSkillGroupListUseCase
import org.bessonov.android_developer.model.converter.RecommendationUiConverter
import org.bessonov.android_developer.model.converter.HardSkillGroupUiConverter
import org.bessonov.android_developer.state.AboutMeState
import javax.inject.Inject

@HiltViewModel
class AboutMeViewModel @Inject constructor(
    getHardSkillGroupListUseCase: GetHardSkillGroupListUseCase,
    getRecommendationListUseCase: GetRecommendationListUseCase,
    getContactUseCase: GetContactUseCase,
    private val hardSkillGroupUiConverter: HardSkillGroupUiConverter,
    private val recommendationUiConverter: RecommendationUiConverter
) : ViewModel() {

    private val isShowGreetingAnim: MutableStateFlow<Boolean> = MutableStateFlow(false)

    private val mail: Flow<String> = getContactUseCase()
        .map { contact ->
            contact.mail
        }

    val uiState: StateFlow<AboutMeState> =
        combine(
            isShowGreetingAnim,
            getHardSkillGroupListUseCase(),
            getRecommendationListUseCase(),
            mail
        ) { isShowGreetingAnim, hardSkillGroupUiList, recommendationList, mail ->
            AboutMeState.Success(
                isShowGreetingAnim = isShowGreetingAnim,
                hardSkillGroupUiList = hardSkillGroupUiConverter.convertEntityListToUiModelList(
                    entityList = hardSkillGroupUiList
                ),
                recommendationsList = recommendationUiConverter.convertEntityListToUiModelList(
                    entityList = recommendationList
                ),
                mail = mail
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
            initialValue = AboutMeState.Loading
        )

    fun greetingAnimShown() {
        isShowGreetingAnim.update { true }
    }
}