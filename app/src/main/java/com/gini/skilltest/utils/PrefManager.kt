package com.gini.skilltest.utils

import android.content.Context
import android.content.SharedPreferences


/**
 * Created by bs206 on 2/1/18.
 */
class PrefManager(context: Context) {
    val pref: SharedPreferences
    val editor: SharedPreferences.Editor

    var PRIVATE_MODE = 0

    var isFirstTimeLaunch: Boolean
        get() = pref.getBoolean(IS_FIRST_TIME_LAUNCH, true)
        set(isFirstTime) {
            editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
            editor.commit()
        }

    fun clearPref(context: Context) {
        val settings: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        settings.edit().clear().commit()
    }

    init {
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    companion object {
        private val PREF_NAME = "Gini"
        private val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"

    }

}