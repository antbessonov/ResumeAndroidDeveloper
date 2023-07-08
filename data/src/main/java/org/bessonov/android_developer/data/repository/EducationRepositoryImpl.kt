package org.bessonov.android_developer.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.bessonov.android_developer.data.database.EducationDao
import org.bessonov.android_developer.data.mapper.EducationMapper
import org.bessonov.android_developer.domain.model.Education

class EducationRepositoryImpl(
    private val educationDao: EducationDao,
    private val educationMapper: EducationMapper
) {

    fun getList(): Flow<List<Education>> {
        return educationDao.getList()
            .map { dbModelList ->
                educationMapper.mapDbModelListToEntityList(dbModelList = dbModelList)
            }
    }
}