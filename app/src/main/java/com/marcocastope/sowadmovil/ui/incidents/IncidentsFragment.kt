package com.marcocastope.sowadmovil.ui.incidents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.marcocastope.sowadmovil.App
import com.marcocastope.sowadmovil.R
import com.marcocastope.sowadmovil.model.Incident
import com.marcocastope.sowadmovil.ui.incidents.add.AddIncidentActivity
import com.marcocastope.sowadmovil.ui.incidents.detail.DetailIncidentActivity
import com.marcocastope.sowadmovil.ui.login.LoginActivity
import com.marcocastope.sowadmovil.ui.navigateTo
import com.marcocastope.sowadmovil.ui.toast


class IncidentsFragment : Fragment(), IncidentContract.ViewInterface {

    companion object {
        const val INTENT_INCIDENT_KEY = "IncidentsFragment:id"
    }

    private val adapter by lazy {
        IncidentListAdapter {
            activity?.navigateTo<DetailIncidentActivity>(
                INTENT_INCIDENT_KEY to it.idIncident
            )
        }
    }
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabIncident: FloatingActionButton
    private lateinit var incidentPresenter: IncidentContract.PresenterInterface
    private var idUser = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_incidents, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.incidentsRecycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        fabIncident = view.findViewById(R.id.fabIncident)
        initListeners()
        setupPresenter()
    }

    override fun onStart() {
        super.onStart()
        //idUser = activity?.intent!!.getIntExtra(LoginActivity.LOGIN_KEY, -1)
        idUser = App.getUserId()
        incidentPresenter.loadIncidents(idUser.toString())
    }

    private fun setupPresenter() {
        val remoteDataSource = App.remoteApi
        incidentPresenter = IncidentPresenter(this, remoteDataSource)
    }

    private fun initListeners() {
        fabIncident.setOnClickListener { activity?.navigateTo<AddIncidentActivity>() }
    }

    override fun showIncidents(incidents: List<Incident>) {
        adapter.setIncidents(incidents)
        activity?.toast("El id del usuario es: $idUser")
    }

    override fun showMessage(message: String) {
        activity?.toast(message)
    }
}