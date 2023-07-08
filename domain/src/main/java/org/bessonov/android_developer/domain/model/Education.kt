package org.bessonov.android_developer.domain.model

data class Education(
    val name: String,
    val group: EducationGroup,
    val degree: String?,
    val company: String,
    val dateStart: String?,
    val dateEnd: String
)