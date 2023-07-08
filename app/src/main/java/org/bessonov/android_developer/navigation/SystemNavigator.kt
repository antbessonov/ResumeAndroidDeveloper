package org.bessonov.android_developer.navigation

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build

fun goToTelegramApp(context: Context, telegramId: String, handleNavigationError: () -> Unit) {
    try {
        val telegramIntent = Intent(
            Intent.ACTION_VIEW, Uri.parse("http://telegram.me/$telegramId")
        )
        val packageName = "org.telegram.messenger"
        if (isPackageInstalled(packageName, context.packageManager)) {
            telegramIntent.`package` = packageName
        }
        context.startActivity(telegramIntent)
    } catch (e: ActivityNotFoundException) {
        handleNavigationError()
    }
}

fun goToPhoneApp(context: Context, tel: String, handleNavigationError: () -> Unit) {
    try {
        val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$tel"))
        context.startActivity(callIntent)
    } catch (e: ActivityNotFoundException) {
        handleNavigationError()
    }
}

fun goToEmailApp(context: Context, mail: String, handleNavigationError: () -> Unit) {
    try {
        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$mail"))
        context.startActivity(emailIntent)
    } catch (e: ActivityNotFoundException) {
        handleNavigationError()
    }
}

fun goToLinkedinApp(context: Context, linkedinId: String, handleNavigationError: () -> Unit) {
    try {
        val linkedinIntent = Intent(
            Intent.ACTION_VIEW, Uri.parse("https://linkedin.com/in/$linkedinId")
        )
        val packageName = "com.linkedin.android"
        if (isPackageInstalled(packageName, context.packageManager)) {
            linkedinIntent.`package` = packageName
        }
        context.startActivity(linkedinIntent)
    } catch (e: ActivityNotFoundException) {
        handleNavigationError()
    }
}

fun goToBrowser(context: Context, url: String, handleNavigationError: () -> Unit) {
    try {
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$url"))
        context.startActivity(webIntent)
    } catch (e: ActivityNotFoundException) {
        handleNavigationError()
    }
}

private fun isPackageInstalled(packageName: String, packageManager: PackageManager): Boolean {
    return try {
        packageManager.getPackageInfoCompat(packageName = packageName)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

private fun PackageManager.getPackageInfoCompat(packageName: String, flags: Int = 0): PackageInfo =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(flags.toLong()))
    } else {
        @Suppress("DEPRECATION") getPackageInfo(packageName, flags)
    }