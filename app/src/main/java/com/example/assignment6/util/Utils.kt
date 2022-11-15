package com.example.assignment6.util

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.example.assignment6.ui.activity.LoginActivity

object Utils {
    const val LIGHT = "light"
    const val DARK = "dark"
}

private lateinit var sharedPref: SharedPreferences

class AppUtils {
    companion object {
        @JvmStatic
        fun setPref(activity: LoginActivity): SharedPreferences {
            sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
            return sharedPref
        }

        fun getSharedPref(): SharedPreferences {
            return sharedPref
        }

        fun getPref(key: String): String? {
            return sharedPref.getString(key, "")
        }

        fun decideTheme(theme: String) {
            if (theme == Utils.DARK) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}


