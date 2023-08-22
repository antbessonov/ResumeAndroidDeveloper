package org.bessonov.android_developer.fragment.about_me.adapter.recommendation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bessonov.android_developer.R
import org.bessonov.android_developer.databinding.AddRecommendationBinding

class AddRecommendationViewHolder(
    val parent: ViewGroup,
    onAddRecommendationClick: (() -> Unit)?
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.add_recommendation,
        parent,
        false
    )
) {
    val binding = AddRecommendationBinding.bind(itemView)

    init {

        binding.addBtn.setOnClickListener {
            onAddRecommendationClick?.invoke()
        }
    }
}