package org.bessonov.android_developer.model

import androidx.annotation.DrawableRes
import org.bessonov.android_developer.R

enum class StoredPhotoRecommenderDrawable(
    @DrawableRes val resId: Int,
    val storedPhoto: Int
) {
    EVGENIY(R.drawable.evgeniy_bessonov, 1);

    companion object {

        @DrawableRes
        fun asRes(storedPhoto: Int): Int {
            return values().first { it.storedPhoto == storedPhoto }.resId
        }
    }
}