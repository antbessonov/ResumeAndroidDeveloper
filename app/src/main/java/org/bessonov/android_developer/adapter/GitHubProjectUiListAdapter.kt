package org.bessonov.android_developer.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.bessonov.android_developer.databinding.GithubProjectItemBinding
import org.bessonov.android_developer.model.GitHubProjectUi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubProjectUiListAdapter @Inject constructor() :
    ListAdapter<GitHubProjectUi, GitHubProjectUiViewHolder>(GitHubProjectUiDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubProjectUiViewHolder {
        return GitHubProjectUiViewHolder(parent = parent)
    }

    override fun onBindViewHolder(holder: GitHubProjectUiViewHolder, position: Int) {
        val gitHubProject = getItem(position)
        setGitHubProjectContent(binding = holder.binding, gitHubProject = gitHubProject)
    }

    private fun setGitHubProjectContent(
        binding: GithubProjectItemBinding,
        gitHubProject: GitHubProjectUi
    ) {
        binding.nameTv.text = gitHubProject.name
        binding.descriptionTv.text = gitHubProject.description
        (binding.listRv.adapter as GitHubProjectUiHardSkillListAdapter)
            .submitList(gitHubProject.hardSkillList)
    }
}