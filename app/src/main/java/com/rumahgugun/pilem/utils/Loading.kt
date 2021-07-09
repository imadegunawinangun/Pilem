package com.rumahgugun.pilem.utils

import android.R.attr.animation
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar


class Loading {
    private fun progressBar(progressBar: ProgressBar?) {
        progressBar!!.visibility = View.VISIBLE
        ObjectAnimator.ofInt(progressBar, "progress", 0,100)
            .setDuration(2000)
            .start()

    }

    fun loadingScreenOff(progressBar: ProgressBar?) {
            progressBar!!.visibility = View.GONE
    }
    fun loadingScreenOn(progressBar: ProgressBar?) {
            progressBar(progressBar)
    }
}