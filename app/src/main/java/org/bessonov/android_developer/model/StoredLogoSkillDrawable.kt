package org.bessonov.android_developer.model

import androidx.annotation.DrawableRes
import org.bessonov.android_developer.R

enum class StoredLogoSkillDrawable(
    @DrawableRes val resId: Int,
    val storedLogo: Int
) {
    KOTLIN(R.drawable.kotlin_logo, 1),
    JAVA(R.drawable.java_logo, 2);

    companion object {

        @DrawableRes
        fun asRes(storedLogo: Int): Int {
            return values().first { it.storedLogo == storedLogo }.resId
        }
    }
}