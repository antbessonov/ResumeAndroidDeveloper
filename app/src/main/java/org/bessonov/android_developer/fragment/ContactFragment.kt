package org.bessonov.android_developer.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.maps.android.ktx.addMarker
import com.google.maps.android.ktx.awaitMap
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.bessonov.android_developer.R
import org.bessonov.android_developer.databinding.FragmentContactBinding
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
        observeViewModelState()
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
        setTelegramClickListener(state = state)
        setAllContactsContent(state = state)
        setMap(state = state)
        setMailClickListener(state = state)
        setPhoneClickListener(state = state)
        setLinkedinClickListener(state = state)
        setGitHubClickListener(state = state)
    }

    private fun hideLoadingProgress() {
        binding.loadingProgress.visibility = View.GONE
    }

    private fun setTelegramClickListener(state: ContactState.Success) {
        binding.telegramIv.setOnClickListener {
            goToTelegramApp(context = requireContext(), telegramId = state.telegramId) {}
        }
        binding.telegram.setOnClickListener {
            goToTelegramApp(context = requireContext(), telegramId = state.telegramId) {}
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
                val myGeo = googleMap?.addMarker {
                    position(myGeoLatLng)
                    title(getString(R.string.my_geo_marker_title))
                }
                myGeo?.showInfoWindow()
                googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(myGeoLatLng, ZOOM_DEFAULT))
            }
        }
    }

    private fun setMailClickListener(state: ContactState.Success) {
        binding.mail.setOnClickListener {
            goToEmailApp(context = requireContext(), mail = state.mail) {}
        }
    }

    private fun setPhoneClickListener(state: ContactState.Success) {
        binding.tel.setOnClickListener {
            goToPhoneApp(context = requireContext(), tel = state.tel) {}
        }
    }

    private fun setLinkedinClickListener(state: ContactState.Success) {
        binding.linkedinIv.setOnClickListener {
            goToLinkedinApp(context = requireContext(), linkedinId = state.linkedinId) {}
        }
    }

    private fun setGitHubClickListener(state: ContactState.Success) {
        binding.githubIv.setOnClickListener {
            goToBrowser(context = requireContext(), url = "github.com/${state.githubId}") {}
        }
    }

    companion object {

        private const val FIRST_OFFSET = 500L
        private const val SECOND_OFFSET = 1000L
        private const val THIRD_OFFSET = 1500L

        private const val ZOOM_DEFAULT = 12F
    }
}