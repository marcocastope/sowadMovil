package com.marcocastope.sowadmovil.ui.intro

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.marcocastope.sowadmovil.App
import com.marcocastope.sowadmovil.R
import com.marcocastope.sowadmovil.ui.incidents.IncidentsFragment
import com.marcocastope.sowadmovil.ui.main.MainActivity
import com.marcocastope.sowadmovil.ui.navigateTo
import com.marcocastope.sowadmovil.ui.register.RegisterActivity

class IntroActivity : AppCompatActivity() {

    private lateinit var btnIntro: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        initUi()
        btnIntro.setOnClickListener {
            navigateTo<RegisterActivity>()
            finish()
        }
        if (App.getToken().isNotBlank()) {
            navigateTo<MainActivity>()
        }
    }

    private fun initUi() {
        btnIntro = findViewById(R.id.btnIntro)
    }

}