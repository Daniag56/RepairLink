package com.revup.features.auth.data.repository

import com.revup.features.auth.data.User
import com.revup.features.auth.data.local.UserPreferencesDatasource
import com.revup.features.auth.data.remote.AuthDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remote: AuthDatasource,
    private val local: UserPreferencesDatasource
): AuthRepository {

    override val user: Flow<Result<User>> = remote.user
    override val token: Flow<String?> = local.token
    override val name: Flow<String?> = local.name

    override suspend fun login(email: String, password: String){
        remote.login(email,password)
        saveSessionIfLoggedIn()
    }
    override suspend fun register(email: String, name: String, password: String){
        remote.register(email,name,password)
        saveSessionIfLoggedIn()
    }
    override suspend fun logout(){
        remote.logout()
        local.clearSession()
    }

    private suspend fun saveSessionIfLoggedIn(){
        remote.user.first().onSuccess { user ->
            local.saveSession(user.token,user.name)
        }
    }

}
