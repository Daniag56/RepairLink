package com.revup.features.auth.data.repository

import com.revup.features.auth.data.User
import com.revup.features.auth.data.remote.AuthDatasource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remote: AuthDatasource
): AuthRepository {
    override val user: Flow<Result<User>> = remote.user
    override suspend fun login(email: String, password: String) = remote.login(email,password)
    override suspend fun register(email: String, name: String, password: String) = remote.register(email,name,password)
    override suspend fun logout() = remote.logout()
}