package com.marcocastope.sowadmovil.ui.incidents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marcocastope.sowadmovil.R
import com.marcocastope.sowadmovil.model.Incident
import com.marcocastope.sowadmovil.util.formatDateToText
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class IncidentListAdapter(private val incidents: MutableList<Incident> = mutableListOf(), private val listener: (Incident)->Unit) :
    RecyclerView.Adapter<IncidentListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_incident_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(incidents[position])
        holder.itemView.setOnClickListener { listener(incidents[position]) }
    }

    override fun getItemCount(): Int = incidents.size
    fun setIncidents(incidents: List<Incident>) {
        this.incidents.clear()
        this.incidents.addAll(incidents)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val incidentId = view.findViewById<TextView>(R.id.incidentId)
        private val description = view.findViewById<TextView>(R.id.description)
        private val street = view.findViewById<TextView>(R.id.street)
        private val date = view.findViewById<TextView>(R.id.date)
        private val status = view.findViewById<TextView>(R.id.status)

        fun bind(incident: Incident) {
            incidentId.text = incident.idIncident.toString()
            description.text = incident.description
            street.text = incident.street.address
            date.text = formatDateToText(incident.date)
            status.text = incident.status.name
        }
    }
}