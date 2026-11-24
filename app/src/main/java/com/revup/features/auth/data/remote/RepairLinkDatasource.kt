package com.revup.features.auth.data.remote

import com.revup.core.remote.RepairLinkApi
import com.revup.features.auth.data.User
import com.revup.features.auth.data.remote.exception.CredentialsNotValidException
import com.revup.features.auth.data.remote.exception.EmailAlreadyRegisteredException
import com.revup.features.auth.data.remote.exception.UserIsNotLoggedInException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class RepairLinkDatasource @Inject constructor(
    private val api: RepairLinkApi
): AuthDatasource {
    private val _user: MutableStateFlow<Result<User>> =
        MutableStateFlow<Result<User>>(Result.failure(UserIsNotLoggedInException()))
    override val user: Flow<Result<User>> = _user.asStateFlow()

    override suspend fun login(email: String, password: String) {
        val response = api.login(loginBody = RepairLinkLoginBody(
            email = email,
            password = password,
        ))

        if (response.isSuccessful) {
            _user.value = Result.success(
                User(
                    email = email,
                    name = response.body()!!.user.name,
                    token = response.body()!!.token
                )
            )
        }
        else {
            _user.value = Result.failure(CredentialsNotValidException())
        }
    }

    override suspend fun register(email: String, name: String, password: String) {
        val response = api.register(registerBody = RepairLinkRegisterBody(
            email = email,
            name = name,
            password = password,
        ))

        if (response.isSuccessful) {
            _user.value = Result.success(
                User(
                    email = email,
                    name = response.body()!!.user.name,
                    token = response.body()!!.token
                )
            )
        }
        else if (response.code() == 409) {
            _user.value = Result.failure(EmailAlreadyRegisteredException())
        }
        else {
            _user.value = Result.failure(CredentialsNotValidException())
        }
    }

    override suspend fun logout() {
        _user.value = Result.failure(UserIsNotLoggedInException())
    }
}
