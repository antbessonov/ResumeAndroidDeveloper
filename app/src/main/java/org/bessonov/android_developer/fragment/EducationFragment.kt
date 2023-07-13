package org.bessonov.android_developer.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.bessonov.android_developer.R
import org.bessonov.android_developer.adapter.EducationUiListAdapter
import org.bessonov.android_developer.databinding.FragmentEducationBinding
import org.bessonov.android_developer.domain.model.EducationGroup
import org.bessonov.android_developer.state.EducationState
import org.bessonov.android_developer.viewmodel.EducationViewModel
import javax.inject.Inject

@AndroidEntryPoint
class EducationFragment : Fragment(R.layout.fragment_education) {

    private val binding: FragmentEducationBinding by viewBinding()

    private val viewModel by lazy {
        ViewModelProvider(this)[EducationViewModel::class.java]
    }

    @Inject
    lateinit var educationListAdapter: EducationUiListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEducationListAdapter()
        setEducationGroupCheckedListener()
        setEducationGroupClickListener()
        observeViewModelState()
    }

    private fun setEducationListAdapter() {
        binding.educationListRv.adapter = educationListAdapter
    }

    private fun setEducationGroupCheckedListener() {
        binding.educationGroupBtn.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.basic_education_btn -> viewModel.updateGroup(group = EducationGroup.BASIC)
                    R.id.additional_education_btn -> viewModel.updateGroup(group = EducationGroup.ADDITIONAL)
                }
            } else {
                if (group.checkedButtonId == View.NO_ID) {
                    viewModel.updateGroup()
                }
            }
        }
    }

    private fun setEducationGroupClickListener() {
        educationListAdapter.onGroupClick = { position ->
            val education = educationListAdapter.currentList[position]
            when (education.group) {
                EducationGroup.BASIC -> {
                    binding.educationGroupBtn.check(R.id.basic_education_btn)
                }
                EducationGroup.ADDITIONAL -> {
                    binding.educationGroupBtn.check(R.id.additional_education_btn)
                }
            }
        }
    }

    private fun observeViewModelState() {
        viewModel.uiState
            .flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle,
                minActiveState = Lifecycle.State.CREATED
            )
            .onEach { state -> handleState(state = state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleState(state: EducationState) {
        when (state) {
            is EducationState.Loading -> reduce(state = state)
            is EducationState.Success -> reduce(state = state)
        }
    }

    private fun reduce(state: EducationState.Loading) {
        showLoadingProgress()
    }

    private fun showLoadingProgress() {
        binding.loadingProgress.visibility = View.VISIBLE
    }

    private fun reduce(state: EducationState.Success) {
        hideLoadingProgress()
        binding.educationListRv.visibility = View.VISIBLE
        educationListAdapter.submitList(state.educationList)
        binding.educationListRv.startLayoutAnimation()
    }

    private fun hideLoadingProgress() {
        binding.loadingProgress.visibility = View.GONE
    }
}