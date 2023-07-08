package org.bessonov.android_developer.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.bessonov.android_developer.data.database.ContactDao
import org.bessonov.android_developer.data.mapper.ContactMapper
import org.bessonov.android_developer.domain.model.Contact
import org.bessonov.android_developer.domain.repository.ContactRepository

class ContactRepositoryImpl(
    private val contactDao: ContactDao,
    private val contactMapper: ContactMapper
) : ContactRepository {

    override fun get(): Flow<Contact> {
        return contactDao.get()
            .map { dbModel ->
                contactMapper.mapDbModelToEntity(dbModel = dbModel)
            }
    }
}