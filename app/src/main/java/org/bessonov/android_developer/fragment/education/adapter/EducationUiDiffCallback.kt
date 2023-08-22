package org.bessonov.android_developer.fragment.education.adapter

import androidx.recyclerview.widget.DiffUtil
import org.bessonov.android_developer.model.EducationUi

object EducationUiDiffCallback : DiffUtil.ItemCallback<EducationUi>() {

    override fun areItemsTheSame(oldItem: EducationUi, newItem: EducationUi): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: EducationUi, newItem: EducationUi): Boolean {
        return oldItem == newItem
    }
}