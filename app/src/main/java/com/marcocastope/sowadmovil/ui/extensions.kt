package com.marcocastope.sowadmovil.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.os.bundleOf
import com.marcocastope.sowadmovil.R

inline fun <reified T : Activity> Context.navigateTo(vararg pairs: Pair<String, Any?>) {
    Intent(this, T::class.java).apply {
        putExtras(bundleOf(*pairs))
        startActivity(this)
    }
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}

