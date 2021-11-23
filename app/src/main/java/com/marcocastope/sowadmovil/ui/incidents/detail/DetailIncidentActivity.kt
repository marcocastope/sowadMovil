package com.marcocastope.sowadmovil.ui.incidents.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.marcocastope.sowadmovil.App
import com.marcocastope.sowadmovil.R
import com.marcocastope.sowadmovil.model.request.IncidentDataRequest
import com.marcocastope.sowadmovil.ui.incidents.IncidentsFragment
import com.marcocastope.sowadmovil.ui.toast
import com.marcocastope.sowadmovil.util.formatDateToText

class DetailIncidentActivity : AppCompatActivity() {

    private val api = App.remoteApi
    private lateinit var detailDescription: TextInputEditText
    private lateinit var detailStreetOption: AutoCompleteTextView
    private lateinit var detailStatus: TextInputEditText
    private lateinit var detailDate: TextInputEditText
    private lateinit var updateBtn: Button
    private lateinit var deleteBtn: Button
    private lateinit var selectedItemText: Any
    private var streetSelection = 0
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_incident)
        initUi()
        id = intent.getIntExtra(IncidentsFragment.INTENT_INCIDENT_KEY, -1)
        loadIncident()
        setupListeners()
    }

    private fun initUi() {
        detailDescription = findViewById(R.id.detailDescriptionET)
        detailStreetOption = findViewById(R.id.detailStreetOption)
        detailStatus = findViewById(R.id.detailStatusET)
        detailDate = findViewById(R.id.detailDateET)
        updateBtn = findViewById(R.id.detailUpdateBtn)
        deleteBtn = findViewById(R.id.detailDeleteBtn)
    }

    private fun loadIncident() {
        api.getIncident(id.toString()) { incident, error ->
            incident?.let {
                detailDescription.setText(it.description)
                detailStreetOption.setText(it.street.address)
                detailStatus.setText(it.status.name)
                detailDate.setText(formatDateToText(it.date))
            }
        }
        api.getAllStreet { streets, error ->
            val adapter = ArrayAdapter(
                this,
                R.layout.list_item,
                streets.map { it.address })
            detailStreetOption.setAdapter(adapter)
            detailStreetOption.setOnItemClickListener { adapterView, view, selectedItem, id ->
                selectedItemText = adapterView.getItemAtPosition(selectedItem)
                for ((i, value) in streets.withIndex()) {
                    if (selectedItemText == value.address) {
                        streetSelection = value.idstreet
                        Log.d(javaClass.simpleName, "indice: $i valor: $streetSelection")
                    }
                }
            }
        }
    }

    private fun setupListeners(){
        updateBtn.setOnClickListener { updateIncident() }
        deleteBtn.setOnClickListener { deleteIncident() }
    }

    private fun updateIncident() {
        val description = detailDescription.text.toString()
        if (description.isNotEmpty()) {
            val incident = IncidentDataRequest(id, description, streetSelection)
            api.updateIncident(id.toString(), incident){ message, error ->
                message?.let { toast(message) }
            }
            finish()
        }
    }

    private fun deleteIncident() {
        api.removeIncident(id.toString()){ message, error ->
            message?.let { toast(message) }
        }
        finish()
    }
}