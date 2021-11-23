package com.marcocastope.sowadmovil.ui.register

import android.os.Bundle
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
import com.marcocastope.sowadmovil.App
import com.marcocastope.sowadmovil.R
import com.marcocastope.sowadmovil.model.request.UserDataRequest
import com.marcocastope.sowadmovil.ui.login.LoginActivity
import com.marcocastope.sowadmovil.ui.navigateTo
import com.marcocastope.sowadmovil.ui.toast
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private val api = App.remoteApi
    private lateinit var firstNameET: EditText
    private lateinit var lastNameET: EditText
    private lateinit var dniET: EditText
    private lateinit var usernameET: EditText
    private lateinit var passwordET: EditText
    private lateinit var rememberPassword: EditText
    private lateinit var loginText: TextView
    private lateinit var registerBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initUi()
        setupListeners()
    }

    private fun initUi() {
        registerBtn = findViewById(R.id.registerBtn)
        firstNameET = findViewById(R.id.registerFirstnameET)
        lastNameET = findViewById(R.id.registerLastnameET)
        dniET = findViewById(R.id.registerDniET)
        usernameET = findViewById(R.id.registerUsernameET)
        passwordET = findViewById(R.id.registerPasswordET)
        rememberPassword = findViewById(R.id.registerRememberPasswordET)
        loginText = findViewById(R.id.loginText)
    }

    private fun setupListeners() {
        registerBtn.setOnClickListener {
            register()
        }
        loginText.setOnClickListener { navigateTo<LoginActivity>() }
    }

    private fun register() {
        val firstname = firstNameET.text.toString()
        val lastname = lastNameET.text.toString()
        val username = usernameET.text.toString()
        val dni = dniET.text.toString()
        val password = passwordET.text.toString()
        val rememberPassword = rememberPassword.text.toString()
        when {
            !firstnameIsValid(firstname) ->  firstNameET.error = "El nombre necesita al menor 3 caracateres"
            !lastnameIsValid(lastname) -> lastNameET.error = "El apellido necesita al menor 4 caracateres"
            !dniIsValid(dni) -> dniET.error = "El dni necesita 8 caracateres"
            !usernameIsValid(username) -> usernameET.error = "El username necesita al menor 5 caracateres"
            !passwordIsValid(password) -> passwordET.error = "La contraseña necesita al menor 8 caracateres"
            else -> {
                val user = UserDataRequest(firstname, lastname, dni, username, password)
                api.registerUser(user) { message, error ->
                    message?.let {
                        toast(message)
                    }
                }
                finish()
                navigateTo<LoginActivity>()
            }
        }
    }

    private fun passwordIsValid(password: String) = password.length >= 8 && password.isNotEmpty()
    private fun dniIsValid(dni: String) = dni.length == 8 && dni.isNotEmpty()
    private fun firstnameIsValid(firstname: String) = firstname.length >= 3 && firstname.isNotEmpty()
    private fun lastnameIsValid(lastname: String) = lastname.length >= 4 && lastname.isNotEmpty()
    private fun usernameIsValid(username: String) = username.length >= 5 && username.isNotEmpty()
}