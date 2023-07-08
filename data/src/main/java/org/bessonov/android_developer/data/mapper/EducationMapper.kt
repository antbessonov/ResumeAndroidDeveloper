package org.bessonov.android_developer.data.mapper

import org.bessonov.android_developer.data.database.model.EducationDbModel
import org.bessonov.android_developer.domain.model.Education
import org.bessonov.android_developer.domain.model.EducationGroup

class EducationMapper {

    fun mapDbModelListToEntityList(dbModelList: List<EducationDbModel>): List<Education> {
        return dbModelList
            .map { dbModel ->
                mapDbModelToEntity(dbModel = dbModel)
            }
    }

    private fun mapDbModelToEntity(dbModel: EducationDbModel): Education {
        return Education(
            name = dbModel.name,
            group = mapGroup(str = dbModel.group),
            degree = dbModel.degree,
            company = dbModel.company,
            dateStart = dbModel.dateStart,
            dateEnd = dbModel.dateEnd
        )
    }

    private fun mapGroup(str: String): EducationGroup {
        return when (str) {
            EducationGroup.BASIC.groupName -> EducationGroup.BASIC
            EducationGroup.ADDITIONAL.groupName -> EducationGroup.ADDITIONAL
            else -> {
                throw RuntimeException("Unknown education group: $str")
            }
        }
    }
}