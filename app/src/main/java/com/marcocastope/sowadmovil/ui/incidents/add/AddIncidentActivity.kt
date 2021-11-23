package com.marcocastope.sowadmovil.ui.incidents.add

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.marcocastope.sowadmovil.App
import com.marcocastope.sowadmovil.R
import com.marcocastope.sowadmovil.model.Incident
import com.marcocastope.sowadmovil.model.Street
import com.marcocastope.sowadmovil.model.request.IncidentDataRequest
import com.marcocastope.sowadmovil.ui.toast

class AddIncidentActivity : AppCompatActivity() {

    private lateinit var addDescription: TextInputEditText

    private val api = App.remoteApi
    private lateinit var autoComplete: AutoCompleteTextView
    private lateinit var addIncidentBtn: Button
    private lateinit var selectedItemText: Any
    private var streetSelection = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_incident)
        initUi()
    }

    private fun initUi() {
        addDescription = findViewById(R.id.addDescriptionET)
        autoComplete = findViewById(R.id.autoComplete2)
        addIncidentBtn = findViewById(R.id.addIncidentBtn)

        api.getAllStreet { streets, error ->
            val adapter = ArrayAdapter(
                this,
                R.layout.list_item,
                streets.map { it.address })
            autoComplete.setAdapter(adapter)
            autoComplete.setOnItemClickListener { adapterView, view, selectedItem, id ->
                selectedItemText = adapterView.getItemAtPosition(selectedItem)
                for ((i, value) in streets.withIndex()) {
                    if (selectedItemText == value.address) {
                        streetSelection = value.idstreet
                        Log.d(javaClass.simpleName, "indice: $i valor: $streetSelection")
                    }
                }
            }
        }
        addIncidentBtn.setOnClickListener { addIncident() }
    }

    private fun addIncident() {
        val description = addDescription.text.toString()
        if (description.isNotBlank()) {
            val incident = IncidentDataRequest(description = description, street = streetSelection, user = App.getUserId())
            api.saveIncident(incident) { message, error ->
                message?.let {
                    toast("$message")
                }
            }
        }
        finish()
    }
}