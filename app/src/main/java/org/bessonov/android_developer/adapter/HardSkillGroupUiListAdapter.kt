package org.bessonov.android_developer.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.bessonov.android_developer.databinding.SkillGroupItemBinding
import org.bessonov.android_developer.model.HardSkillGroupUi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HardSkillGroupUiListAdapter @Inject constructor() :
    ListAdapter<HardSkillGroupUi, HardSkillGroupUiViewHolder>(HardSkillGroupUiDiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HardSkillGroupUiViewHolder {
        return HardSkillGroupUiViewHolder(parent = parent)
    }

    override fun onBindViewHolder(holder: HardSkillGroupUiViewHolder, position: Int) {
        val skillGroup = getItem(position)
        setProgrammingLanguageContent(binding = holder.binding, skillGroup = skillGroup)
    }

    private fun setProgrammingLanguageContent(
        binding: SkillGroupItemBinding,
        skillGroup: HardSkillGroupUi
    ) {
        binding.nameTv.text = skillGroup.name
        (binding.skillListRv.adapter as HardSkillUiListAdapter).submitList(skillGroup.hardSkillList)
    }
}