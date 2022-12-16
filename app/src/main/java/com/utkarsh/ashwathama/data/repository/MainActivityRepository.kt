package com.utkarsh.ashwathama.data.repository

import IResult
import com.utkarsh.ashwathama.data.models.LoginResponseModel
import com.utkarsh.ashwathama.network.api.MainActivityApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainActivityRepository @Inject constructor(
    private val api: MainActivityApi
) {

    // Get User Details Api Below
    fun getUserLoginInfo(userId: String, passWord: String): Flow<IResult<LoginResponseModel?>> {
        return flow {
            emit(getUserLoginInfoApi(userId, passWord))
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun getUserLoginInfoApi(
        userId: String,
        passWord: String
    ): IResult<LoginResponseModel?> {
        val result = api.getUserLogin(userId, passWord)
        return when (result.status) {
            IResult.Status.SUCCESS -> {

                val addNewUserInfo = result.data
                if (addNewUserInfo != null) {
                    withContext(Dispatchers.IO) {
                        //  customersDAO.insertOrUpdateAddNewCustomerInfo(addNewCustomer)
                    }
                }
                result
            }
            IResult.Status.ERROR -> {
                IResult.response(result.message.toString())
            }
            else -> IResult.response(result.message.toString())
        }
    }

    fun getDeviceDetailsInfo(userId: String, passWord: String): Flow<IResult<LoginResponseModel?>> {
        return flow {
            emit(getDeviceDetailsInfoApi(userId, passWord))
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun getDeviceDetailsInfoApi(
        userId: String,
        passWord: String
    ): IResult<LoginResponseModel?> {
        val result = api.getDeviceDetails(userId, passWord)
        return when (result.status) {
            IResult.Status.SUCCESS -> {

                val addDeviceDetailsInfo = result.data
                if (addDeviceDetailsInfo != null) {
                    withContext(Dispatchers.IO) {

                    }
                }
                result
            }
            IResult.Status.ERROR -> {
                IResult.response(result.message.toString())
            }
            else -> IResult.response(result.message.toString())
        }
    }
}