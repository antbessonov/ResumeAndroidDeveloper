package org.bessonov.android_developer.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.bessonov.android_developer.R
import org.bessonov.android_developer.databinding.GithubProjectItemBinding
import org.bessonov.android_developer.model.GitHubProjectUi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubProjectUiListAdapter @Inject constructor() :
    ListAdapter<GitHubProjectUi, GitHubProjectUiViewHolder>(GitHubProjectUiDiffCallback) {

    var onClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubProjectUiViewHolder {
        return GitHubProjectUiViewHolder(parent = parent, onClick = onClick)
    }

    override fun onBindViewHolder(holder: GitHubProjectUiViewHolder, position: Int) {
        val gitHubProject = getItem(position)
        setGitHubProjectContent(binding = holder.binding, gitHubProject = gitHubProject)
    }

    private fun setGitHubProjectContent(
        binding: GithubProjectItemBinding,
        gitHubProject: GitHubProjectUi
    ) {
        val resources = binding.root.resources
        binding.nameTv.text = gitHubProject.name
        binding.descriptionTv.text = gitHubProject.description
        (binding.hardSkillListRv.adapter as GitHubProjectUiHardSkillListAdapter)
            .submitList(gitHubProject.hardSkillList)
        binding.dateUpdateTv.text = resources.getString(
            R.string.date_update, gitHubProject.dateUpdate
        )
    }
}