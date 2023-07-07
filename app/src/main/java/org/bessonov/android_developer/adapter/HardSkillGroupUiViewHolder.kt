package org.bessonov.android_developer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bessonov.android_developer.R
import org.bessonov.android_developer.databinding.SkillGroupItemBinding

class HardSkillGroupUiViewHolder(val parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.skill_group_item,
        parent,
        false
    )
) {
    val binding = SkillGroupItemBinding.bind(itemView)

    init {
        val hardSkillUiListAdapter = HardSkillUiListAdapter()
        binding.skillListRv.adapter = hardSkillUiListAdapter
    }
}