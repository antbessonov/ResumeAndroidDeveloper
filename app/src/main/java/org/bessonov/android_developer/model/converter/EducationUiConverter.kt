package org.bessonov.android_developer.model.converter

import org.bessonov.android_developer.domain.model.Education
import org.bessonov.android_developer.model.EducationUi
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class EducationUiConverter @Inject constructor() {

    fun convertEntityListToUiModelList(entityList: List<Education>): List<EducationUi> {
        return entityList.map { entity ->
            convertEntityToUiModel(entity = entity)
        }
    }

    private fun convertEntityToUiModel(entity: Education): EducationUi {
        return EducationUi(
            name = entity.name.replaceFirstChar(Char::uppercaseChar),
            group = entity.group,
            degree = entity.degree?.replaceFirstChar(Char::uppercaseChar),
            company = entity.company,
            dateStart = convertDate(str = entity.dateStart),
            dateEnd = convertDate(str = entity.dateEnd)
        )
    }

    private fun convertDate(str: String?): String {
        if (str == null) {
            return EMPTY_DATE
        }
        val dateInput = SimpleDateFormat("MMyyyy", Locale.getDefault()).parse(str)
        val sdf = SimpleDateFormat("MMM yyyy", Locale.getDefault())
        return sdf.format(dateInput as Date)
    }

    companion object {

        private const val EMPTY_DATE = ""
    }
}