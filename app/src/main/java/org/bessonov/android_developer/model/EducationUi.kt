package org.bessonov.android_developer.model

import org.bessonov.android_developer.domain.model.EducationGroup

data class EducationUi(
    val name: String,
    val group: EducationGroup,
    val degree: String?,
    val company: String,
    val dateStart: String?,
    val dateEnd: String
)
