package org.bessonov.android_developer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bessonov.android_developer.R
import org.bessonov.android_developer.databinding.GithubProjectItemBinding

class GitHubProjectUiViewHolder(val parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.github_project_item,
        parent,
        false
    )
) {
    val binding = GithubProjectItemBinding.bind(itemView)

    init {
        val gitHubProjectUiHardSkillListAdapter = GitHubProjectUiHardSkillListAdapter()
        binding.hardSkillListRv.adapter = gitHubProjectUiHardSkillListAdapter
    }
}