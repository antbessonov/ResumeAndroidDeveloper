package org.bessonov.android_developer.data.network.model

import com.google.gson.annotations.SerializedName

data class GitHubProjectDto(
    val name: String,
    val description: String,
    @SerializedName("updated_at")
    val dateUpdate: String,
    val topics: List<String>
)
