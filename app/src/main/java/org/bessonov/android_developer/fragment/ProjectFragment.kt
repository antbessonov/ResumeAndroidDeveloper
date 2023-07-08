package org.bessonov.android_developer.fragment

import android.os.Bundle
import android.util.Log
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
import org.bessonov.android_developer.adapter.GitHubProjectUiListAdapter
import org.bessonov.android_developer.databinding.FragmentProjectBinding
import org.bessonov.android_developer.domain.util.LoadingError
import org.bessonov.android_developer.domain.util.NetworkProblem
import org.bessonov.android_developer.domain.util.SomethingWentWrong
import org.bessonov.android_developer.fragment.LoadingErrorDialogFragment.Companion.LOADING_ERROR
import org.bessonov.android_developer.fragment.LoadingErrorDialogFragment.Companion.NETWORK_PROBLEM
import org.bessonov.android_developer.fragment.LoadingErrorDialogFragment.Companion.SOMETHING_WENT_WRONG
import org.bessonov.android_developer.state.ProjectState
import org.bessonov.android_developer.viewmodel.ProjectViewModel
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
        setRefreshListener()
        observeViewModelState()
        observeLoadingError()
        setupLoadingErrorDialogFragmentListener()
    }

    private fun setGitHubProjectAdapter() {
        binding.githubProjectListRv.adapter = gitHubProjectAdapter
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
        Log.d("QAS", state.toString())
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
        binding.swipeRefreshLayout.visibility = View.VISIBLE
        gitHubProjectAdapter.submitList(state.gitHubProjectList)
        if (state.isLoadingResultReceived == true) {
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun hideLoadingProgress() {
        binding.loadingProgress.visibility = View.GONE
    }

    private fun observeLoadingError() {
        viewModel.loadingError
            .flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle,
                minActiveState = Lifecycle.State.RESUMED
            )
            .onEach { loadingError -> handleLoadingError(loadingError = loadingError) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleLoadingError(loadingError: LoadingError?) {
        when (loadingError) {
            NetworkProblem -> {
                navigateLoadingErrorDialogFragment(loadingError = NETWORK_PROBLEM)
            }
            SomethingWentWrong -> {
                navigateLoadingErrorDialogFragment(loadingError = SOMETHING_WENT_WRONG)
            }
            null -> Unit
        }
    }

    private fun navigateLoadingErrorDialogFragment(loadingError: String) {
        if (findNavController().currentDestination?.id == R.id.navigation_project) {
            findNavController()
                .navigate(
                    R.id.action_navigation_project_to_navigation_loading_error,
                    bundleOf(LOADING_ERROR to loadingError)
                )
        }
    }

    private fun setupLoadingErrorDialogFragmentListener() {
        LoadingErrorDialogFragment.setupListener(
            manager = parentFragmentManager,
            lifecycleOwner = this
        ) {
            viewModel.hideLoadingError()
        }
    }
}