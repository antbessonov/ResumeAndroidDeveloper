package org.bessonov.android_developer.fragment.about_me.adapter.hard_skill

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.bessonov.android_developer.model.HardSkillUi

class HardSkillUiListAdapter :
    ListAdapter<HardSkillUi, HardSkillUiViewHolder>(HardSkillUiDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HardSkillUiViewHolder {
        return HardSkillUiViewHolder(parent = parent)
    }

    override fun onBindViewHolder(holder: HardSkillUiViewHolder, position: Int) {
        val hardSkill = getItem(position)
        if (hardSkill.logo == null) {
            holder.binding.logoIv.visibility = View.GONE
            holder.binding.nameTv.text = hardSkill.name
        } else {
            holder.binding.logoIv.setImageResource(hardSkill.logo)
            holder.binding.nameTv.text = hardSkill.name
        }
    }
}