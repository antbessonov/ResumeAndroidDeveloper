package org.bessonov.android_developer.fragment.education.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bessonov.android_developer.R
import org.bessonov.android_developer.databinding.EducationItemBinding

class EducationUiViewHolder(val parent: ViewGroup, onGroupClick: ((Int) -> Unit)?) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.education_item,
            parent,
            false
        )
    ) {

    val binding = EducationItemBinding.bind(itemView)

    init {
        binding.groupTv.setOnClickListener {
            onGroupClick?.invoke(adapterPosition)
        }
    }
}