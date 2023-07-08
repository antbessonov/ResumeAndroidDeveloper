package org.bessonov.android_developer.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.LifecycleOwner
import org.bessonov.android_developer.R
import kotlin.properties.Delegates

class LoadingErrorDialogFragment : DialogFragment() {

    private var loadingError by Delegates.notNull<String>()

    private var title by Delegates.notNull<String>()
    private var message by Delegates.notNull<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
        initialValue()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .create()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        setFragmentResult(requestKey = REQUEST_KEY, result = bundleOf(RESPONSE_KEY to CANCEL))
    }

    private fun parseParams() {
        val args = requireArguments()
        val error = args.getString(LOADING_ERROR)
        if (error != NETWORK_PROBLEM && error != SOMETHING_WENT_WRONG) {
            throw RuntimeException("Unknown loading error: $error.")
        }
        loadingError = error
    }

    private fun initialValue() {
        when (loadingError) {
            NETWORK_PROBLEM -> {
                title = resources.getString(R.string.network_problem)
                message = resources.getString(R.string.error_network_problem_description)
            }
            SOMETHING_WENT_WRONG -> {
                title = resources.getString(R.string.something_went_wrong)
                message = resources.getString(R.string.error_something_went_wrong_description)
            }
        }
    }

    companion object {

        private const val TAG: String = "LoadingErrorDialogFragment"
        const val REQUEST_KEY = "$TAG:defaultRequestKey"

        const val RESPONSE_KEY = "RESPONSE"
        const val CANCEL = "cancel"

        const val LOADING_ERROR = "loading_error"

        const val NETWORK_PROBLEM = "network_problem"
        const val SOMETHING_WENT_WRONG = "something_went_wrong"

        fun setupListener(
            manager: FragmentManager,
            lifecycleOwner: LifecycleOwner,
            cancelListener: () -> Unit
        ) {
            manager.setFragmentResultListener(REQUEST_KEY, lifecycleOwner) { _, result ->
                when (result.getString(RESPONSE_KEY)) {
                    CANCEL -> cancelListener.invoke()
                }
            }
        }
    }
}