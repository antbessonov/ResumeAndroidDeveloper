package org.bessonov.android_developer.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.animation.AnimationUtils
import org.bessonov.android_developer.R

const val shortAnimationDuration = 500L

fun View.fadeIn(startDelay: Long = 0) {
    alpha = 0f
    visibility = View.VISIBLE
    animate()
        .alpha(1.0f)
        .setDuration(shortAnimationDuration)
        .setStartDelay(startDelay)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                alpha = 1.0f
            }
        })
}

fun View.fadeOut(startDelay: Long = 0) {
    animate()
        .alpha(0.0f)
        .setDuration(shortAnimationDuration)
        .setStartDelay(startDelay)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                alpha = 0.0f
            }
        })
}

fun View.slideIn(startOffset: Long = 0, direction: AnimInDirection = AnimInDirection.RIGHT) {
    visibility = View.VISIBLE
    val animation = when (direction) {
        AnimInDirection.LEFT -> AnimationUtils.loadAnimation(context, R.anim.slide_in_left)
        AnimInDirection.RIGHT -> AnimationUtils.loadAnimation(context, R.anim.slide_in_right)
        AnimInDirection.UP -> AnimationUtils.loadAnimation(context, R.anim.slide_in_up)
        AnimInDirection.DOWN -> AnimationUtils.loadAnimation(context, R.anim.slide_in_down)
    }
    animation.startOffset = startOffset
    this.animation = animation
}

fun View.slideOut(startOffset: Long = 0, direction: AnimOutDirection) {
    val animation = when (direction) {
        AnimOutDirection.LEFT -> AnimationUtils.loadAnimation(context, R.anim.slide_out_left)
        AnimOutDirection.RIGHT -> AnimationUtils.loadAnimation(context, R.anim.slide_out_right)
    }
    animation.startOffset = startOffset
    this.animation = animation
    visibility = View.GONE
}

enum class AnimInDirection {
    LEFT, RIGHT, UP, DOWN
}

enum class AnimOutDirection {
    LEFT, RIGHT
}