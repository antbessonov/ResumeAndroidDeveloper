package org.bessonov.android_developer.fragment.about_me.adapter.recommendation

import androidx.recyclerview.widget.DiffUtil
import org.bessonov.android_developer.model.RecommendationUi

object RecommendationUiDiffCallback : DiffUtil.ItemCallback<RecommendationUi>() {

    override fun areItemsTheSame(oldItem: RecommendationUi, newItem: RecommendationUi): Boolean {
        return oldItem.recommender.name == newItem.recommender.name
    }

    override fun areContentsTheSame(oldItem: RecommendationUi, newItem: RecommendationUi): Boolean {
        return oldItem == newItem
    }
}