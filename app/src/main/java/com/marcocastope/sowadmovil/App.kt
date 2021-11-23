package com.marcocastope.sowadmovil

import android.app.Application
import android.content.Context
import com.marcocastope.sowadmovil.data.networking.RemoteApi
import com.marcocastope.sowadmovil.data.networking.buildApiService

class App : Application() {

    companion object {
        private const val PREFERENCES_KEY = "incident_preferences"
        private const val PREFERENCES_USER_KEY = "user_preferences"
        private const val PREFERENCES_USERNAME_KEY = "username_preferences"
        private lateinit var instance: App

        private val preferences by lazy {
            instance.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)
        }

        fun saveToken(token: String) {
            preferences.edit().putString(PREFERENCES_KEY, token).apply()
        }

        fun getToken() = preferences.getString(PREFERENCES_KEY, "") ?: ""

        private val preferencesUserId by lazy {
            instance.getSharedPreferences(
                PREFERENCES_USER_KEY,
                Context.MODE_PRIVATE
            )
        }

        fun saveUserId(userId: Int) {
            preferencesUserId.edit().putInt(PREFERENCES_USER_KEY, userId).apply()
        }

        fun getUserId() = preferencesUserId.getInt(PREFERENCES_USER_KEY, -1)

        private val preferencesUsername by lazy {
            instance.getSharedPreferences(
                PREFERENCES_USERNAME_KEY, Context.MODE_PRIVATE
            )
        }

        fun saveUsername(username: String) {
            preferencesUsername.edit().putString(PREFERENCES_USERNAME_KEY, username).apply()
        }

        fun getUsername() = preferencesUsername.getString(PREFERENCES_USERNAME_KEY, "") ?: ""

        private val buildApiService by lazy { buildApiService() }
        val remoteApi by lazy { RemoteApi(buildApiService) }

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}