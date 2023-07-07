package org.bessonov.android_developer.adapter

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