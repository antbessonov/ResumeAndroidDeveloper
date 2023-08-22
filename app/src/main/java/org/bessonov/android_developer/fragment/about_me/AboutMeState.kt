package org.bessonov.android_developer.fragment.about_me

import org.bessonov.android_developer.model.RecommendationUi
import org.bessonov.android_developer.model.HardSkillGroupUi

sealed class AboutMeState {

    object Loading : AboutMeState()

    data class Success(
        val isShowGreetingAnim: Boolean,
        val hardSkillGroupUiList: List<HardSkillGroupUi>,
        val recommendationsList: List<RecommendationUi>,
        val mail: String,
    ) : AboutMeState()
}