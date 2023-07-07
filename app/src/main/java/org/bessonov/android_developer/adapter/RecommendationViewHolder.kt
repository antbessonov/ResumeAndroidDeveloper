package org.bessonov.android_developer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bessonov.android_developer.R
import org.bessonov.android_developer.databinding.RecommendationItemBinding

class RecommendationViewHolder(val parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.recommendation_item,
        parent,
        false
    )
) {
    val binding = RecommendationItemBinding.bind(itemView)
}