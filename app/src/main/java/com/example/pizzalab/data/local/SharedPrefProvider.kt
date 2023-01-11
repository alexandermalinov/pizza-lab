package com.example.pizzalab.data.local

import android.content.Context
import com.example.pizzalab.utils.common.USER_SHARED_PREFS_EMAIL
import com.example.pizzalab.utils.common.USER_SHARED_PREFS_IS_SIGNED_IN_KEY
import com.example.pizzalab.utils.common.USER_SHARED_PREFS_KEY

object SharedPrefProvider {

    suspend fun setSignedInUserEmail(context: Context, email: String) {
        context.getSharedPreferences(USER_SHARED_PREFS_KEY, Context.MODE_PRIVATE)
            .edit()
            .putString(USER_SHARED_PREFS_EMAIL, email)
            .apply()
    }

    suspend fun setIsUserSignedIn(context: Context, isUserSignedIn: Boolean) {
        context.getSharedPreferences(USER_SHARED_PREFS_KEY, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(USER_SHARED_PREFS_IS_SIGNED_IN_KEY, isUserSignedIn)
            .apply()
    }

    suspend fun getIsUserSignedIn(context: Context) =
        context.getSharedPreferences(USER_SHARED_PREFS_KEY, Context.MODE_PRIVATE)
            .getBoolean(USER_SHARED_PREFS_IS_SIGNED_IN_KEY, false)
}