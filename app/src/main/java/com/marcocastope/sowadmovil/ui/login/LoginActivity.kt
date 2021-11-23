package com.marcocastope.sowadmovil.ui.login

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.marcocastope.sowadmovil.App
import com.marcocastope.sowadmovil.R
import com.marcocastope.sowadmovil.model.request.LoginDataRequest
import com.marcocastope.sowadmovil.model.responses.LoginResponse
import com.marcocastope.sowadmovil.ui.main.MainActivity
import com.marcocastope.sowadmovil.ui.navigateTo
import com.marcocastope.sowadmovil.ui.register.RegisterActivity
import com.marcocastope.sowadmovil.ui.toast

class LoginActivity : AppCompatActivity() {

    companion object {
        const val LOGIN_KEY = "LoginActivity:id"
    }
    private val api = App.remoteApi
    private lateinit var username: TextView
    private lateinit var password: TextView
    private lateinit var registerTextView: TextView
    private lateinit var loginBtn: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initUi()
        setupListeners()
    }

    private fun initUi() {
        username = findViewById(R.id.loginUsernameET)
        password = findViewById(R.id.loginPasswordET)
        registerTextView = findViewById(R.id.registerText)
        loginBtn = findViewById(R.id.loginBtn)
    }

    private fun setupListeners() {
        loginBtn.setOnClickListener { loginUser() }
        registerTextView.setOnClickListener { navigateTo<RegisterActivity>() }
    }

    private fun loginUser() {
        val username = username.text.toString()
        val password = password.text.toString()
        if (username.isNotEmpty() && password.isNotEmpty()) {
            val loginDataRequest = LoginDataRequest(username, password)
            api.loginUser(loginDataRequest) { response, error ->
                response?.let {
                    loginSuccess(response)
                }
            }
        } else {
            toast("Los campos son requeridos.")
        }
    }

    private fun loginSuccess(response: LoginResponse) {
        App.saveToken("Bearer ${response.jwt}")
        App.saveUserId(response.user.iduser)
        App.saveUsername(response.user.username)
        navigateTo<MainActivity>()
        finish()
    }


}