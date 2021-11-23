package com.marcocastope.sowadmovil.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.marcocastope.sowadmovil.App
import com.marcocastope.sowadmovil.R
import com.marcocastope.sowadmovil.ui.login.LoginActivity
import com.marcocastope.sowadmovil.ui.navigateTo

class ProfileFragment : Fragment() {

    private val api = App.remoteApi
    private lateinit var logoutBtn: Button
    private lateinit var profileFirstname: TextView
    private lateinit var profileLastname: TextView
    private lateinit var profileUsername: TextView
    private lateinit var profileDni: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi(view)
        setupListeners()
    }

    private fun initUi(view: View) {
        profileFirstname = view.findViewById(R.id.profileFirstname)
        profileLastname = view.findViewById(R.id.profileLastname)
        profileUsername = view.findViewById(R.id.profileUsername)
        profileDni = view.findViewById(R.id.profileDni)
        logoutBtn = view.findViewById(R.id.logoutBtn)
        displayProfile()
    }

    private fun displayProfile() {
        api.getProfile(App.getUsername()) { user, error ->
            user?.let {
                profileFirstname.text = it.firstname
                profileLastname.text = it.lastname
                profileUsername.text = it.username
                profileDni.text = it.dni
            }
        }
    }

    private fun setupListeners() {
        logoutBtn.setOnClickListener { logout() }
    }

    private fun logout() {
        App.saveToken("")
        App.saveUserId(-1)
        App.saveUsername("")
        activity?.navigateTo<LoginActivity>()
        activity?.finish()
    }

}