package org.bessonov.android_developer.fragment.about_me.adapter.hard_skill

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bessonov.android_developer.R
import org.bessonov.android_developer.databinding.HardSkillGroupItemBinding

class HardSkillGroupUiViewHolder(val parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.hard_skill_group_item,
        parent,
        false
    )
) {
    val binding = HardSkillGroupItemBinding.bind(itemView)

    init {
        val hardSkillUiListAdapter = HardSkillUiListAdapter()
        binding.hardSkillListRv.adapter = hardSkillUiListAdapter
    }
}