package org.bessonov.android_developer.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.bessonov.android_developer.domain.model.Contact
import org.bessonov.android_developer.domain.repository.ContactRepository

class GetContactUseCase(private val repository: ContactRepository) {

    operator fun invoke(): Flow<Contact> {
        return repository.get()
    }
}