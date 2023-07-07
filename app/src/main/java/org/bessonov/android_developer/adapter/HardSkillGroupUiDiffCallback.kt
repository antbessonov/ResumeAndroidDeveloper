package org.bessonov.android_developer.adapter

import androidx.recyclerview.widget.DiffUtil
import org.bessonov.android_developer.model.HardSkillGroupUi

object HardSkillGroupUiDiffCallback : DiffUtil.ItemCallback<HardSkillGroupUi>() {

    override fun areItemsTheSame(oldItem: HardSkillGroupUi, newItem: HardSkillGroupUi): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: HardSkillGroupUi, newItem: HardSkillGroupUi): Boolean {
        return oldItem == newItem
    }
}