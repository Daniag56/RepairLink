package com.revup.features.auth.data.remote

import com.revup.features.auth.data.User
import kotlinx.coroutines.flow.Flow

interface AuthDatasource {
    val user: Flow<Result<User>>
    suspend fun login(email: String, password: String)
    suspend fun register(email: String, name: String, password: String)
    suspend fun logout()
}
