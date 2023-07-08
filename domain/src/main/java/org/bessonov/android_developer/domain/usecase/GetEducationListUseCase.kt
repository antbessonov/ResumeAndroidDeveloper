package org.bessonov.android_developer.domain.usecase

import org.bessonov.android_developer.domain.model.Education
import org.bessonov.android_developer.domain.model.EducationGroup

class GetEducationListUseCase {

    operator fun invoke(
        educationList: List<Education>,
        group: EducationGroup? = null
    ): List<Education> {
        return if (group == null) {
            educationList
        } else {
            educationList.filter { education ->
                education.group == group
            }
        }
    }
}