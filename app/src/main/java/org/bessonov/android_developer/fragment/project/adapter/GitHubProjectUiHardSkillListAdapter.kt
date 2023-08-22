package org.bessonov.android_developer.fragment.project.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.bessonov.android_developer.fragment.about_me.adapter.hard_skill.HardSkillUiDiffCallback
import org.bessonov.android_developer.model.HardSkillUi

class GitHubProjectUiHardSkillListAdapter :
    ListAdapter<HardSkillUi, GitHubProjectUiHardSkillViewHolder>(HardSkillUiDiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GitHubProjectUiHardSkillViewHolder {
        return GitHubProjectUiHardSkillViewHolder(parent = parent)
    }

    override fun onBindViewHolder(holder: GitHubProjectUiHardSkillViewHolder, position: Int) {
        val hardSkill = getItem(position)
        holder.binding.nameTv.text = hardSkill.name
    }
}