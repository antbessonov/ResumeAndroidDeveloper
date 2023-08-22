package org.bessonov.android_developer.fragment.project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bessonov.android_developer.R
import org.bessonov.android_developer.databinding.GithubProjectHardSkillItemBinding

class GitHubProjectUiHardSkillViewHolder(val parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.github_project_hard_skill_item,
        parent,
        false
    )
) {
    val binding = GithubProjectHardSkillItemBinding.bind(itemView)
}