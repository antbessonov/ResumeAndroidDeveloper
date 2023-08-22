package org.bessonov.android_developer.fragment.education

import org.bessonov.android_developer.domain.model.EducationGroup
import org.bessonov.android_developer.model.EducationUi

sealed class EducationState {

    object Loading : EducationState()

    data class Success(
        val group: EducationGroup? = null,
        val educationList: List<EducationUi>
    ) : EducationState()
}