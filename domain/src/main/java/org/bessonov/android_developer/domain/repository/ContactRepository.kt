package org.bessonov.android_developer.domain.repository

import kotlinx.coroutines.flow.Flow
import org.bessonov.android_developer.domain.model.Contact

interface ContactRepository {

    fun get(): Flow<Contact>
}