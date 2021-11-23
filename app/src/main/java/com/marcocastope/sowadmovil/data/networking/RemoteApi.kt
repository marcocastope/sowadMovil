package com.marcocastope.sowadmovil.data.networking

import com.google.gson.Gson
import com.marcocastope.sowadmovil.App
import com.marcocastope.sowadmovil.model.Incident
import com.marcocastope.sowadmovil.model.Street
import com.marcocastope.sowadmovil.model.User
import com.marcocastope.sowadmovil.model.request.IncidentDataRequest
import com.marcocastope.sowadmovil.model.request.LoginDataRequest
import com.marcocastope.sowadmovil.model.request.UserDataRequest
import com.marcocastope.sowadmovil.model.responses.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteApi(private val remoteApiService: RemoteApiService) {

    companion object {
        const val API_URL = "http://10.0.3.2:8080/api/"
    }

    private val gson = Gson()

    fun registerUser(
        userDataRequest: UserDataRequest,
        onUserRegister: (String?, Throwable?) -> Unit
    ) {
        remoteApiService.registerUser(userDataRequest).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                val responseBody = response.body()?.string()
                if (responseBody == null) {
                    onUserRegister(null, NullPointerException("No data available"))
                    return
                }
                val jsonData = gson.fromJson(responseBody, MessageResponse::class.java)
                if (jsonData != null && jsonData.message.isNotBlank()) {
                    onUserRegister(jsonData.message, null)
                } else {
                    onUserRegister(null, NullPointerException("No data available"))
                }
            }

            override fun onFailure(call: Call<ResponseBody>, error: Throwable) {
                onUserRegister(null, error)
            }
        })
    }

    fun loginUser(
        loginDataRequest: LoginDataRequest,
        onUserLoginReceive: (LoginResponse?, Throwable?) -> Unit
    ) {
        remoteApiService.loginUser(loginDataRequest).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val responseData = response.body()?.string()
                if (responseData == null) {
                    onUserLoginReceive(null, NullPointerException("No data available"))
                    return
                }
                val jsonData = gson.fromJson(responseData, LoginResponse::class.java)
                if (jsonData != null) {
                    onUserLoginReceive(jsonData, null)
                } else {
                    onUserLoginReceive(null, NullPointerException("No data available"))
                }
            }

            override fun onFailure(call: Call<ResponseBody>, error: Throwable) {
                onUserLoginReceive(null, error)
            }

        })
    }

    fun getAllIncident(idUser: String, onIncidentsReceive: (List<Incident>, Throwable?) -> Unit) {
        remoteApiService.getAllIncidents(idUser, App.getToken())
            .enqueue(object : Callback<GetIncidentsResponse> {
                override fun onResponse(
                    call: Call<GetIncidentsResponse>,
                    response: Response<GetIncidentsResponse>
                ) {
                    val responseBody = response.body()
                    if (responseBody != null && responseBody.incidents.isNotEmpty()) {
                        onIncidentsReceive(responseBody.incidents, null)
                    } else {
                        onIncidentsReceive(emptyList(), NullPointerException("No data available"))
                    }
                }

                override fun onFailure(call: Call<GetIncidentsResponse>, error: Throwable) {
                    onIncidentsReceive(emptyList(), error)
                }
            })
    }

    fun getIncident(idIncident: String, onIncidentReceive: (Incident?, Throwable?) -> Unit) {
        remoteApiService.getIncident(App.getToken(), idIncident)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val responseData = response.body()?.string()
                    if (responseData == null) {
                        onIncidentReceive(null, NullPointerException("No data available"))
                        return
                    }
                    val jsonData = gson.fromJson(responseData, Incident::class.java)
                    if (jsonData != null) {
                        onIncidentReceive(jsonData, null)
                    } else {
                        onIncidentReceive(null, NullPointerException("No data available"))
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, error: Throwable) {
                    onIncidentReceive(null, error)
                }

            })
    }

    fun saveIncident(
        incidentDataRequest: IncidentDataRequest,
        onSaveIncident: (String?, Throwable?) -> Unit
    ) {
        remoteApiService.saveIncident(App.getToken(), incidentDataRequest)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val responseData = response.body()?.string()
                    if (responseData == null) {
                        onSaveIncident(null, NullPointerException("No data"))
                        return
                    }
                    val jsonData = gson.fromJson(responseData, MessageResponse::class.java)
                    if (jsonData != null && jsonData.message.isNotBlank()) {
                        onSaveIncident(jsonData.message, null)
                    } else {
                        onSaveIncident(null, NullPointerException("No data"))
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, error: Throwable) {
                    onSaveIncident(null, error)
                }

            })
    }

    fun getAllStreet(onStreetReceive: (List<Street>, Throwable?) -> Unit) {
        remoteApiService.getAllStreet(App.getToken()).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val responseData = response.body()?.string()
                if (responseData == null) {
                    onStreetReceive(emptyList(), NullPointerException("No data available"))
                    return
                }
                val jsonData = gson.fromJson(responseData, StreetResponse::class.java)
                if (jsonData != null && jsonData.streets.isNotEmpty()) {
                    onStreetReceive(jsonData.streets, null)
                } else {
                    onStreetReceive(emptyList(), NullPointerException("No data available"))
                }
            }

            override fun onFailure(call: Call<ResponseBody>, error: Throwable) {
                onStreetReceive(emptyList(), error)
            }

        })
    }

    fun getProfile(username: String, onProfileReceive: (User?, Throwable?) -> Unit) {
        remoteApiService.getProfile(App.getToken(), username)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val responseBody = response.body()?.string()
                    if (responseBody == null) {
                        onProfileReceive(null, NullPointerException("No data available"))
                        return
                    }
                    val jsonBody = gson.fromJson(responseBody, User::class.java)
                    if (jsonBody != null) {
                        onProfileReceive(jsonBody, null)
                    } else {
                        onProfileReceive(null, NullPointerException("No data available"))
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, error: Throwable) {
                    onProfileReceive(null, error)
                }

            })
    }

    fun updateIncident(
        idIncident: String,
        incidentDataRequest: IncidentDataRequest,
        onUpdateIncident: (String?, Throwable?) -> Unit
    ) {
        remoteApiService.updateIncident(App.getToken(), idIncident, incidentDataRequest)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val responseData = response.body()?.string()
                    if (responseData == null) {
                        onUpdateIncident(null, NullPointerException("No data available"))
                        return
                    }
                    val jsonData = gson.fromJson(responseData, GenericResponse::class.java)
                    if (jsonData != null && jsonData.message.isEmpty()) {
                        onUpdateIncident(jsonData.message, null)
                    } else {
                        onUpdateIncident(null, NullPointerException("No data available"))
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, error: Throwable) {
                    onUpdateIncident(null, error)
                }

            })
    }

    fun removeIncident(idIncident: String, onDeleteIncidentReceive: (String?, Throwable?) -> Unit) {
        remoteApiService.removeIncident(App.getToken(), idIncident)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val responseData = response.body()?.string()
                    if (responseData == null) {
                        onDeleteIncidentReceive(null, NullPointerException("No data available"))
                        return
                    }
                    val jsonData = gson.fromJson(responseData, GenericResponse::class.java)
                    if (jsonData != null && jsonData.message.isNotBlank()) {
                        onDeleteIncidentReceive(jsonData.message, null)
                    } else {
                        onDeleteIncidentReceive(null, NullPointerException("No data available"))
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, error: Throwable) {
                    onDeleteIncidentReceive(null, error)
                }

            })
    }

}