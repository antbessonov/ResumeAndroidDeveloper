package org.bessonov.android_developer.fragment.about_me.adapter.hard_skill

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bessonov.android_developer.R
import org.bessonov.android_developer.databinding.HardSkillItemBinding

class HardSkillUiViewHolder(
    val parent: ViewGroup
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.hard_skill_item,
        parent,
        false
    )
) {
    val binding = HardSkillItemBinding.bind(itemView)
}