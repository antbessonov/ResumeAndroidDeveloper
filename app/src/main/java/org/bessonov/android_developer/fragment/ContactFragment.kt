package org.bessonov.android_developer.fragment

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.ktx.addMarker
import com.google.maps.android.ktx.awaitMap
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.bessonov.android_developer.R
import org.bessonov.android_developer.databinding.FragmentContactBinding
import org.bessonov.android_developer.domain.util.ErrorMessage
import org.bessonov.android_developer.domain.util.NetworkProblem
import org.bessonov.android_developer.domain.util.OperationFailed
import org.bessonov.android_developer.fragment.ErrorMessageDialogFragment.Companion.NETWORK_PROBLEM
import org.bessonov.android_developer.fragment.ErrorMessageDialogFragment.Companion.OPERATION_FAILED
import org.bessonov.android_developer.navigation.*
import org.bessonov.android_developer.state.ContactState
import org.bessonov.android_developer.util.*
import org.bessonov.android_developer.viewmodel.ContactViewModel

@AndroidEntryPoint
class ContactFragment : Fragment(R.layout.fragment_contact) {

    private val binding: FragmentContactBinding by viewBinding()

    private val viewModel by lazy {
        ViewModelProvider(this)[ContactViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showMainCommunication()
        setAllContactsClickListener()
        setUpdateMapClickListener()
        observeViewModelState()
        observeErrorMessage()
        setupErrorMessageDialogFragmentListener()
    }

    private fun showMainCommunication() {
        binding.telegramIv.slideIn(direction = AnimInDirection.LEFT)
        binding.telegramTv.slideIn(direction = AnimInDirection.LEFT)
        binding.feedbackIv.slideIn(direction = AnimInDirection.RIGHT)
        binding.feedbackTv.slideIn(direction = AnimInDirection.RIGHT)
        binding.allContactsTv.slideIn(startOffset = FIRST_OFFSET, direction = AnimInDirection.UP)
    }

    private fun setAllContactsClickListener() {
        binding.allContactsTv.setOnClickListener {
            viewModel.isInternetConnection()
            hideMainCommunication()
            showAllContacts()
        }
    }

    private fun hideMainCommunication() {
        binding.telegramIv.slideOut(direction = AnimOutDirection.LEFT)
        binding.telegramTv.slideOut(direction = AnimOutDirection.LEFT)
        binding.feedbackIv.slideOut(direction = AnimOutDirection.RIGHT)
        binding.feedbackTv.slideOut(direction = AnimOutDirection.RIGHT)
        binding.allContactsTv.fadeOut()
    }

    private fun showAllContacts() {
        binding.mainContactsTitleTv.fadeIn(startDelay = FIRST_OFFSET)
        binding.mainContactsCard.slideIn(
            startOffset = FIRST_OFFSET,
            direction = AnimInDirection.DOWN
        )
        binding.myGeoCard.slideIn(startOffset = SECOND_OFFSET, direction = AnimInDirection.DOWN)
        binding.linkedinIv.slideIn(startOffset = THIRD_OFFSET, direction = AnimInDirection.DOWN)
        binding.githubIv.slideIn(startOffset = THIRD_OFFSET, direction = AnimInDirection.DOWN)
    }

    private fun setUpdateMapClickListener() {
        binding.updateMapBtn.setOnClickListener {
            viewModel.updateMap()
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

    private fun handleState(state: ContactState) {
        when (state) {
            is ContactState.Loading -> reduce(state = state)
            is ContactState.Success -> reduce(state = state)
        }
    }

    private fun reduce(state: ContactState.Loading) {
        showLoadingProgress()
    }

    private fun showLoadingProgress() {
        binding.loadingProgress.visibility = View.VISIBLE
    }

    private fun reduce(state: ContactState.Success) {
        hideLoadingProgress()
        setFeedbackClickListener(state = state)
        setTelegramClickListener(state = state)
        setAllContactsContent(state = state)
        setMap(state = state)
        showDefaultMap(state = state)
        showUpdateMapProgress(state = state)
        setMailClickListener(state = state)
        setPhoneClickListener(state = state)
        setLinkedinClickListener(state = state)
        setGitHubClickListener(state = state)
    }

    private fun hideLoadingProgress() {
        binding.loadingProgress.visibility = View.GONE
    }

    private fun setFeedbackClickListener(state: ContactState.Success) {
        binding.feedbackIv.setOnClickListener {
            goToEmailApp(context = requireContext(), mail = state.mail) {
                viewModel.handleNavigationError()
            }
        }
    }

    private fun setTelegramClickListener(state: ContactState.Success) {
        binding.telegramIv.setOnClickListener {
            goToTelegramApp(context = requireContext(), telegramId = state.telegramId) {
                viewModel.handleNavigationError()
            }
        }
        binding.telegram.setOnClickListener {
            goToTelegramApp(context = requireContext(), telegramId = state.telegramId) {
                viewModel.handleNavigationError()
            }
        }
    }

    private fun setAllContactsContent(state: ContactState.Success) {
        binding.telegram.setValue(state.telegramId)
        binding.mail.setValue(state.mail)
        binding.tel.setValue(state.tel)
    }

    private fun setMap(state: ContactState.Success) {
        val myGeoLatLng = state.myGeo
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                val mapFragment: SupportMapFragment? =
                    childFragmentManager.findFragmentById(R.id.my_geo_map) as? SupportMapFragment
                val googleMap = mapFragment?.awaitMap()
                googleMapNightMode(googleMap = googleMap)
                val myGeo = googleMap?.addMarker {
                    position(myGeoLatLng)
                    title(getString(R.string.my_geo_marker_title))
                }
                myGeo?.showInfoWindow()
                googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(myGeoLatLng, ZOOM_DEFAULT))
            }
        }
    }

    private fun googleMapNightMode(googleMap: GoogleMap?) {
        if (isDarkThemeOn()) {
            googleMap?.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    requireContext(),
                    R.raw.mapstyle_night
                )
            )
        }
    }

    private fun isDarkThemeOn(): Boolean {
        return (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == UI_MODE_NIGHT_YES
    }

    private fun showDefaultMap(state: ContactState.Success) {
        val fragment = childFragmentManager.findFragmentById(R.id.my_geo_map)
        val fragmentTransition = childFragmentManager.beginTransaction()
        fragmentTransition.setCustomAnimations(
            android.R.animator.fade_in,
            android.R.animator.fade_out
        )
        if (state.isInternetConnection) {
            fragment?.let { fragmentTransition.show(it) }
            binding.defaultMapLayout.fadeOut()
            viewModel.stopUpdateMap()
        } else {
            fragment?.let { fragmentTransition.hide(it) }
            if (binding.defaultMapLayout.visibility == View.GONE) {
                binding.defaultMapLayout.fadeIn()
            }
        }
        fragmentTransition.commit()
    }

    private fun showUpdateMapProgress(state: ContactState.Success) {
        if (state.isUpdateMapProgress) {
            binding.updateMapProgress.visibility = View.VISIBLE
        } else {
            binding.updateMapProgress.visibility = View.GONE
        }
    }

    private fun setMailClickListener(state: ContactState.Success) {
        binding.mail.setOnClickListener {
            goToEmailApp(context = requireContext(), mail = state.mail) {
                viewModel.handleNavigationError()
            }
        }
    }

    private fun setPhoneClickListener(state: ContactState.Success) {
        binding.tel.setOnClickListener {
            goToPhoneApp(context = requireContext(), tel = state.tel) {
                viewModel.handleNavigationError()
            }
        }
    }

    private fun setLinkedinClickListener(state: ContactState.Success) {
        binding.linkedinIv.setOnClickListener {
            goToLinkedinApp(context = requireContext(), linkedinId = state.linkedinId) {
                viewModel.handleNavigationError()
            }
        }
    }

    private fun setGitHubClickListener(state: ContactState.Success) {
        binding.githubIv.setOnClickListener {
            goToBrowser(context = requireContext(), url = "github.com/${state.githubId}") {
                viewModel.handleNavigationError()
            }
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
            NetworkProblem -> navigateErrorMessageDialogFragment(errorMessage = NETWORK_PROBLEM)
            OperationFailed -> navigateErrorMessageDialogFragment(errorMessage = OPERATION_FAILED)
            else -> Unit
        }
    }

    private fun navigateErrorMessageDialogFragment(errorMessage: String) {
        if (findNavController().currentDestination?.id == R.id.navigation_contact) {
            findNavController()
                .navigate(
                    R.id.action_navigation_contact_to_navigation_loading_error,
                    bundleOf(ErrorMessageDialogFragment.ERROR_MESSAGE to errorMessage)
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

    companion object {

        private const val FIRST_OFFSET = 500L
        private const val SECOND_OFFSET = 1000L
        private const val THIRD_OFFSET = 1500L

        private const val ZOOM_DEFAULT = 12F
    }
}