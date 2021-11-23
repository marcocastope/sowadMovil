package com.marcocastope.sowadmovil.ui.incidents

import com.marcocastope.sowadmovil.data.networking.RemoteApi

class IncidentPresenter(
    private val view: IncidentContract.ViewInterface,
    private val remoteDataSource: RemoteApi
) : IncidentContract.PresenterInterface {
    override fun loadIncidents(idUser: String) {
        remoteDataSource.getAllIncident(idUser) { incidents, error ->
            view.showIncidents(incidents)
        }
    }
}