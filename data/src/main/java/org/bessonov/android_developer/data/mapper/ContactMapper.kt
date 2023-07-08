package org.bessonov.android_developer.data.mapper

import org.bessonov.android_developer.data.database.model.ContactDbModel
import org.bessonov.android_developer.domain.model.Contact
import org.bessonov.android_developer.domain.model.Geo

class ContactMapper {

    fun mapDbModelToEntity(dbModel: ContactDbModel): Contact {
        return Contact(
            telegramId = dbModel.telegramId,
            mail = dbModel.mail,
            tel = dbModel.tel,
            linkedinId = dbModel.linkedinId,
            githubId = dbModel.githubId,
            myGeo = Geo(latitude = dbModel.latitude, longitude = dbModel.longitude)
        )
    }
}