package com.marcocastope.sowadmovil.ui.incidents

import com.marcocastope.sowadmovil.model.Incident

class IncidentContract {

    interface PresenterInterface {
        fun loadIncidents(idUser: String)
    }

    interface ViewInterface {
        fun showIncidents(incidents: List<Incident>)
        fun showMessage(message: String)
    }
}