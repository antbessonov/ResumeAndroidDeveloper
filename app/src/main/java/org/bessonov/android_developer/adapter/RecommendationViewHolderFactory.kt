package org.bessonov.android_developer.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bessonov.android_developer.adapter.RecommendationViewPagerAdapter.Companion.VIEW_TYPE_ADD_RECOMMENDATION
import org.bessonov.android_developer.adapter.RecommendationViewPagerAdapter.Companion.VIEW_TYPE_RECOMMENDATION
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecommendationViewHolderFactory @Inject constructor() {

    fun create(
        parent: ViewGroup,
        viewType: Int,
        onAddRecommendationClick: (() -> Unit)? = null
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_RECOMMENDATION -> RecommendationViewHolder(parent = parent)
            VIEW_TYPE_ADD_RECOMMENDATION -> AddRecommendationViewHolder(
                parent = parent,
                onAddRecommendationClick = onAddRecommendationClick
            )
            else -> {
                throw RuntimeException("Unknown view type: $viewType")
            }
        }
    }
}