package org.bessonov.android_developer.domain.model

data class GitHubProject(
    val name: String,
    val description: String,
    val dateCreation: String,
    val hardSkillGroupList: List<HardSkillGroup>
)
