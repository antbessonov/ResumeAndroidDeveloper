package org.bessonov.android_developer.adapter

import androidx.recyclerview.widget.DiffUtil
import org.bessonov.android_developer.model.GitHubProjectUi

object GitHubProjectUiDiffCallback : DiffUtil.ItemCallback<GitHubProjectUi>() {

    override fun areItemsTheSame(oldItem: GitHubProjectUi, newItem: GitHubProjectUi): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: GitHubProjectUi, newItem: GitHubProjectUi): Boolean {
        return oldItem == newItem
    }
}