package org.bessonov.android_developer.fragment.project

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.bessonov.android_developer.R
import org.bessonov.android_developer.fragment.project.adapter.GitHubProjectUiListAdapter
import org.bessonov.android_developer.databinding.FragmentProjectBinding
import org.bessonov.android_developer.domain.util.*
import org.bessonov.android_developer.fragment.dialog.ErrorMessageDialogFragment
import org.bessonov.android_developer.fragment.dialog.ErrorMessageDialogFragment.Companion.ERROR_MESSAGE
import org.bessonov.android_developer.fragment.dialog.ErrorMessageDialogFragment.Companion.NETWORK_PROBLEM
import org.bessonov.android_developer.fragment.dialog.ErrorMessageDialogFragment.Companion.OPERATION_FAILED
import org.bessonov.android_developer.fragment.dialog.ErrorMessageDialogFragment.Companion.SOMETHING_WENT_WRONG
import org.bessonov.android_developer.navigation.goToBrowser
import javax.inject.Inject

@AndroidEntryPoint
class ProjectFragment : Fragment(R.layout.fragment_project) {

    private val binding: FragmentProjectBinding by viewBinding()

    private val viewModel by lazy {
        ViewModelProvider(this)[ProjectViewModel::class.java]
    }

    @Inject
    lateinit var gitHubProjectAdapter: GitHubProjectUiListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setGitHubProjectAdapter()
        setGitHubProjectClickListener()
        setRefreshListener()
        observeViewModelState()
        observeErrorMessage()
        setupErrorMessageDialogFragmentListener()
    }

    private fun setGitHubProjectAdapter() {
        binding.githubProjectListRv.adapter = gitHubProjectAdapter
    }

    private fun setGitHubProjectClickListener() {
        gitHubProjectAdapter.onClick = { position ->
            val gitHubProject = gitHubProjectAdapter.currentList[position]
            goToBrowser(
                context = requireContext(),
                url = "github.com/antbessonov/${gitHubProject.name}"
            ) { viewModel.handleNavigationError() }
        }
    }

    private fun setRefreshListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadGitHubProjectList()
        }
    }

    private fun observeViewModelState() {
        viewModel.uiState
            .flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle,
                minActiveState = Lifecycle.State.RESUMED
            )
            .onEach { state -> handleState(state = state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleState(state: ProjectState) {
        when (state) {
            is ProjectState.Loading -> reduce(state = state)
            is ProjectState.Success -> reduce(state = state)
        }
    }

    private fun reduce(state: ProjectState.Loading) {
        showLoadingProgress()
    }

    private fun showLoadingProgress() {
        binding.loadingProgress.visibility = View.VISIBLE
    }

    private fun reduce(state: ProjectState.Success) {
        hideLoadingProgress()
        showSwipeRefreshLayout()
        showEmptyGithubProjectList(state = state)
        refreshStateChange(state = state)
        gitHubProjectAdapter.submitList(state.gitHubProjectList)
    }

    private fun hideLoadingProgress() {
        binding.loadingProgress.visibility = View.GONE
    }

    private fun showSwipeRefreshLayout() {
        if (binding.swipeRefreshLayout.visibility != View.VISIBLE) {
            binding.swipeRefreshLayout.visibility = View.VISIBLE
        }
    }

    private fun refreshStateChange(state: ProjectState.Success) {
        when (state.loadingResult) {
            LoadingResult.Loading -> Unit
            is LoadingResult.Success, is LoadingResult.Error -> {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun showEmptyGithubProjectList(state: ProjectState.Success) {
        if ((state.loadingResult is LoadingResult.Error).and(state.gitHubProjectList.isEmpty())) {
            binding.emptyGithubProjectListIv.visibility = View.VISIBLE
            binding.emptyGithubProjectListTitleTv.visibility = View.VISIBLE
        } else {
            binding.emptyGithubProjectListIv.visibility = View.GONE
            binding.emptyGithubProjectListTitleTv.visibility = View.GONE
        }
    }

    private fun observeErrorMessage() {
        viewModel.errorMessage
            .flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle,
                minActiveState = Lifecycle.State.RESUMED
            )
            .onEach { errorMessage -> handleErrorMessage(errorMessage = errorMessage) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleErrorMessage(errorMessage: ErrorMessage?) {
        when (errorMessage) {
            NetworkProblem -> {
                navigateErrorMessageDialogFragment(errorMessage = NETWORK_PROBLEM)
            }
            SomethingWentWrong -> {
                navigateErrorMessageDialogFragment(errorMessage = SOMETHING_WENT_WRONG)
            }
            OperationFailed -> {
                navigateErrorMessageDialogFragment(errorMessage = OPERATION_FAILED)
            }
            null -> Unit
        }
    }

    private fun navigateErrorMessageDialogFragment(errorMessage: String) {
        if (findNavController().currentDestination?.id == R.id.navigation_project) {
            findNavController()
                .navigate(
                    R.id.action_navigation_project_to_navigation_loading_error,
                    bundleOf(ERROR_MESSAGE to errorMessage)
                )
        }
    }

    private fun setupErrorMessageDialogFragmentListener() {
        ErrorMessageDialogFragment.setupListener(
            manager = parentFragmentManager,
            lifecycleOwner = this,
            positiveListener = viewModel::hideErrorMessage,
            cancelListener = viewModel::hideErrorMessage
        )
    }
}