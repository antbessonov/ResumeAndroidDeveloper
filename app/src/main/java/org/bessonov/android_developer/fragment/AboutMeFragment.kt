package org.bessonov.android_developer.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.bessonov.android_developer.R
import org.bessonov.android_developer.adapter.HardSkillGroupUiListAdapter
import org.bessonov.android_developer.adapter.RecommendationViewPagerAdapter
import org.bessonov.android_developer.databinding.FragmentAboutMeBinding
import org.bessonov.android_developer.navigation.goToEmailApp
import org.bessonov.android_developer.state.AboutMeState
import org.bessonov.android_developer.util.*
import org.bessonov.android_developer.viewmodel.AboutMeViewModel
import javax.inject.Inject

@AndroidEntryPoint
class AboutMeFragment : Fragment(R.layout.fragment_about_me) {

    private val binding: FragmentAboutMeBinding by viewBinding()

    private val viewModel by lazy {
        ViewModelProvider(this)[AboutMeViewModel::class.java]
    }

    @Inject
    lateinit var hardSkillGroupUiListAdapter: HardSkillGroupUiListAdapter

    @Inject
    lateinit var recommendationsAdapter: RecommendationViewPagerAdapter

    @Inject
    lateinit var pageTransformer: ZoomOutPageTransformer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSkillGroupListAdapter()
        setRecommendationViewPager()
        observeViewModelState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.greetingAnimShown()
    }

    private fun setSkillGroupListAdapter() {
        binding.hardSkillGroupListRv.adapter = hardSkillGroupUiListAdapter
    }

    private fun setRecommendationViewPager() {
        binding.recommendationListVp.adapter = recommendationsAdapter
        binding.recommendationListVp.setPageTransformer(pageTransformer)
        binding.recommendationListVp.removeOverScroll()
        TabLayoutMediator(binding.recommendationListTabs, binding.recommendationListVp) { _, _ ->
        }.attach()
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

    private fun handleState(state: AboutMeState) {
        when (state) {
            is AboutMeState.Loading -> reduce(state = state)
            is AboutMeState.Success -> reduce(state = state)
        }
    }

    private fun reduce(state: AboutMeState.Loading) {
        showLoadingProgress()
    }

    private fun showLoadingProgress() {
        binding.loadingProgress.visibility = View.VISIBLE
    }

    private fun reduce(state: AboutMeState.Success) {
        hideLoadingProgress()
        showMyPhoto(state = state)
        showSkillList(state = state)
        showRecommendationList(state = state)
        setAddRecommendationClickListener(state = state)
        hardSkillGroupUiListAdapter.submitList(state.hardSkillGroupUiList)
        recommendationsAdapter.submitList(state.recommendationsList)
    }

    private fun hideLoadingProgress() {
        binding.loadingProgress.visibility = View.GONE
    }

    private fun showMyPhoto(state: AboutMeState.Success) {
        if (state.isShowGreetingAnim) {
            showMyPhotoContent()
        } else {
            showMyPhotoContentWithGreetingAnim()
        }
    }

    private fun showMyPhotoContentWithGreetingAnim() {
        with(binding) {
            myPhotoBackground.fadeIn()
            myPhotoIv.fadeIn()
            helloTv.slideIn()
            myNameTv.slideIn(startOffset = FIRST_OFFSET)
            androidDeveloperTv.slideIn(startOffset = SECOND_OFFSET)
            inJacketTv.fadeIn(startDelay = THIRD_OFFSET)
        }
    }

    private fun showMyPhotoContent() {
        with(binding) {
            myPhotoBackground.fadeIn()
            myPhotoIv.fadeIn()
            helloTv.fadeIn()
            myNameTv.fadeIn()
            androidDeveloperTv.fadeIn()
            inJacketTv.fadeIn()
        }
    }

    private fun showSkillList(state: AboutMeState.Success) {
        val startDelay = if (state.isShowGreetingAnim) {
            WITHOUT_OFFSET
        } else {
            FOURTH_OFFSET
        }
        binding.skillListTitleTv.fadeIn(startDelay = startDelay)
        binding.hardSkillGroupListRv.fadeIn(startDelay = startDelay)
    }

    private fun showRecommendationList(state: AboutMeState.Success) {
        val startDelay = if (state.isShowGreetingAnim) {
            WITHOUT_OFFSET
        } else {
            FOURTH_OFFSET
        }
        binding.recommendationListBackground.fadeIn(startDelay = startDelay)
        binding.recommendationListTitleTv.fadeIn(startDelay = startDelay)
        binding.recommendationListVp.fadeIn(startDelay = startDelay)
        binding.recommendationListTabs.fadeIn(startDelay = startDelay)
    }

    private fun setAddRecommendationClickListener(state: AboutMeState.Success) {
        recommendationsAdapter.onAddRecommendationClick = {
            goToEmailApp(context = requireContext(), mail = state.mail) {}
        }
    }

    companion object {

        private const val WITHOUT_OFFSET = 0L
        private const val FIRST_OFFSET = 600L
        private const val SECOND_OFFSET = 1200L
        private const val THIRD_OFFSET = 1800L
        private const val FOURTH_OFFSET = 2400L
    }
}

