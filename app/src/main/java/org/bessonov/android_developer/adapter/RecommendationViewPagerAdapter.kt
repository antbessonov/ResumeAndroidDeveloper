package org.bessonov.android_developer.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.bessonov.android_developer.R
import org.bessonov.android_developer.databinding.RecommendationItemBinding
import org.bessonov.android_developer.model.RecommendationUi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecommendationViewPagerAdapter @Inject constructor(
    private val recommendationViewHolderFactory: RecommendationViewHolderFactory
) :
    ListAdapter<RecommendationUi, RecyclerView.ViewHolder>(RecommendationUiDiffCallback) {

    var onAddRecommendationClick: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return recommendationViewHolderFactory.create(
            parent = parent,
            viewType = viewType,
            onAddRecommendationClick = onAddRecommendationClick
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RecommendationViewHolder -> setRecommendationContent(
                binding = holder.binding,
                position = position
            )
            is AddRecommendationViewHolder -> Unit
        }
    }

    private fun setRecommendationContent(binding: RecommendationItemBinding, position: Int) {
        val recommendation = getItem(position)
        val resources = binding.root.resources
        binding.photoRecommenderTv.setImageResource(recommendation.recommender.photoResId)
        binding.recommendationTv.text = recommendation.text
        binding.nameRecommenderTv.text = recommendation.recommender.name
        binding.jobTitleRecommenderTv.text = resources.getString(
            R.string.job_title_with_company_name,
            recommendation.recommender.jobTitle,
            recommendation.recommender.nameCompany
        )
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == currentList.size) {
            VIEW_TYPE_ADD_RECOMMENDATION
        } else {
            VIEW_TYPE_RECOMMENDATION
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + ONE_ADD_BUTTON
    }

    companion object {

        const val VIEW_TYPE_RECOMMENDATION = 1
        const val VIEW_TYPE_ADD_RECOMMENDATION = -1

        const val ONE_ADD_BUTTON = 1
    }
}