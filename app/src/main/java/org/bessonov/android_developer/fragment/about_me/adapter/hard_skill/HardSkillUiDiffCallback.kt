package org.bessonov.android_developer.fragment.about_me.adapter.hard_skill

import androidx.recyclerview.widget.DiffUtil
import org.bessonov.android_developer.model.HardSkillUi

object HardSkillUiDiffCallback : DiffUtil.ItemCallback<HardSkillUi>() {

    override fun areItemsTheSame(oldItem: HardSkillUi, newItem: HardSkillUi): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: HardSkillUi, newItem: HardSkillUi): Boolean {
        return oldItem == newItem
    }
}