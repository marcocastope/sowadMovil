package com.marcocastope.sowadmovil.data.networking

import com.marcocastope.sowadmovil.model.request.IncidentDataRequest
import com.marcocastope.sowadmovil.model.request.LoginDataRequest
import com.marcocastope.sowadmovil.model.request.UserDataRequest
import com.marcocastope.sowadmovil.model.responses.GetIncidentsResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface RemoteApiService {

    @POST("register")
    fun registerUser(@Body requestBody: UserDataRequest): Call<ResponseBody>

    @POST("auth")
    fun loginUser(@Body requestBody: LoginDataRequest): Call<ResponseBody>

    @GET("incident/getAll/{id}")
    fun getAllIncidents(
        @Path("id") idUser: String,
        @Header("Authorization") token: String
    ): Call<GetIncidentsResponse>

    @GET("incident/find/{id}")
    fun getIncident(
        @Header("Authorization") token: String,
        @Path("id") idIncident: String
    ): Call<ResponseBody>

    @GET("street/getAll")
    fun getAllStreet(@Header("Authorization") token: String): Call<ResponseBody>

    @GET("user-profile/{username}")
    fun getProfile(
        @Header("Authorization") token: String,
        @Path("username") username: String
    ): Call<ResponseBody>

    @POST("incident/save")
    fun saveIncident(
        @Header("Authorization") token: String,
        @Body requestBody: IncidentDataRequest
    ): Call<ResponseBody>

    @PUT("incident/modify/{id}")
    fun updateIncident(
        @Header("Authorization") token: String,
        @Path("id") idIncident: String,
        @Body requestBody: IncidentDataRequest
    ): Call<ResponseBody>

    @DELETE("incident/remove/{id}")
    fun removeIncident(
        @Header("Authorization") token: String,
        @Path("id") idIncident: String
    ): Call<ResponseBody>
}