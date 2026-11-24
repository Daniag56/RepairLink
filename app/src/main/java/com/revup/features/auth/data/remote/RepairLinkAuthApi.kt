package com.revup.features.auth.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RepairLinkAuthApi {
    @POST("api/auth/login")
    suspend fun login(@Body loginBody: RepairLinkLoginBody): Response<RepairLinkLoginResponse>

    @POST("api/auth/register")
    suspend fun register(@Body registerBody: RepairLinkRegisterBody): Response<RepairLinkLoginResponse>
}

data class RepairLinkLoginBody(
    val email: String,
    val password: String,
)

data class RepairLinkRegisterBody(
    val email: String,
    val name: String,
    val password: String,
)

data class RepairLinkLoginResponse(
    val user: RepairLinkUserResponse,
    val token: String,
)

data class RepairLinkUserResponse(
    val id: Int,
    val email: String,
    val name: String,
)

