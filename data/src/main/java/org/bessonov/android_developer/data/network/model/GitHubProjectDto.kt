package org.bessonov.android_developer.data.network.model

import com.google.gson.annotations.SerializedName

data class GitHubProjectDto(
    val name: String,
    val description: String,
    @SerializedName("created_at")
    val dateCreation: String,
    val topics: List<String>
)
