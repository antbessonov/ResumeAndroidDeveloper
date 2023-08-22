package org.bessonov.android_developer.fragment.education.adapter

import android.content.res.Resources
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.bessonov.android_developer.R
import org.bessonov.android_developer.databinding.EducationItemBinding
import org.bessonov.android_developer.domain.model.EducationGroup
import org.bessonov.android_developer.model.EducationUi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EducationUiListAdapter @Inject constructor() :
    ListAdapter<EducationUi, EducationUiViewHolder>(EducationUiDiffCallback) {

    var onGroupClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationUiViewHolder {
        return EducationUiViewHolder(parent = parent, onGroupClick = onGroupClick)
    }

    override fun onBindViewHolder(holder: EducationUiViewHolder, position: Int) {
        val resources = holder.itemView.resources
        val education = getItem(position)
        setEducationContent(resources = resources, binding = holder.binding, education = education)
    }

    private fun setEducationContent(
        resources: Resources,
        binding: EducationItemBinding,
        education: EducationUi,
    ) {
        binding.companyTv.text = education.company
        setName(resources = resources, binding = binding, education = education)
        setData(resources = resources, binding = binding, education = education)
        setGroupName(binding = binding, education = education)
    }

    private fun setName(
        resources: Resources,
        binding: EducationItemBinding,
        education: EducationUi
    ) {
        if (education.degree == null) {
            binding.nameTv.text = education.name
        } else {
            binding.nameTv.text = resources.getString(
                R.string.education_name_with_degree,
                education.degree,
                education.name
            )
        }
    }

    private fun setData(
        resources: Resources,
        binding: EducationItemBinding,
        education: EducationUi
    ) {
        binding.dataTv.text = resources.getString(
            R.string.education_data_start_with_end,
            education.dateStart,
            education.dateEnd
        )
    }

    private fun setGroupName(
        binding: EducationItemBinding,
        education: EducationUi,
    ) {
        when (education.group) {
            EducationGroup.BASIC -> binding.groupTv.text = EducationGroup.BASIC.groupName
            EducationGroup.ADDITIONAL -> binding.groupTv.text = EducationGroup.ADDITIONAL.groupName
        }
    }

}