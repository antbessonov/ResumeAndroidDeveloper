package org.bessonov.android_developer.domain.model

data class GitHubProject(
    val name: String,
    val description: String,
    val dateUpdate: String,
    val hardSkillGroupList: List<HardSkillGroup>
)
