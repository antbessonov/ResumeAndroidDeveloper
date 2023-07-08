package org.bessonov.android_developer.model

data class GitHubProjectUi(
    val name: String,
    val description: String,
    val dateCreation: String,
    val hardSkillList: List<HardSkillUi>
)