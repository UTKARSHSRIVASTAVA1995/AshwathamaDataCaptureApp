package com.utkarsh.ashwathama.data.repository

import IResult
import com.utkarsh.ashwathama.data.models.*
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

    // Change User Password Api Below
    fun changePasswordInfo(changePasswordModel: ChangePasswordModel): Flow<IResult<Boolean?>> {
        return flow {
            emit(changePasswordInfoApi(changePasswordModel))
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun changePasswordInfoApi(changePasswordModel: ChangePasswordModel): IResult<Boolean?> {
        val result = api.changePassword(changePasswordModel)
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

    fun getDeviceDetailsInfo(userId: String): Flow<IResult<DeviceDetailsListResponseModel?>> {
        return flow {
            emit(getDeviceDetailsInfoApi(userId))
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun getDeviceDetailsInfoApi(
        userId: String
    ): IResult<DeviceDetailsListResponseModel?> {
        val result = api.getDeviceDetails(userId)
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

    fun getDeviceCallLogsInfo(userId: String): Flow<IResult<DeviceCallLogsResponseModel?>> {
        return flow {
            emit(getDeviceCallLogsInfoApi(userId))
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun getDeviceCallLogsInfoApi(
        userId: String
    ): IResult<DeviceCallLogsResponseModel?> {
        val result = api.getDeviceCallLogs(userId)
        return when (result.status) {
            IResult.Status.SUCCESS -> {

                val addDeviceCallLogsInfo = result.data
                if (addDeviceCallLogsInfo != null) {
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

    fun getDeviceLocationInfo(userId: String): Flow<IResult<DeviceLocationResponseModel?>> {
        return flow {
            emit(getDeviceLocationInfoApi(userId))
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun getDeviceLocationInfoApi(
        userId: String
    ): IResult<DeviceLocationResponseModel?> {
        val result = api.getDeviceLocation(userId)
        return when (result.status) {
            IResult.Status.SUCCESS -> {

                val addDeviceCallLogsInfo = result.data
                if (addDeviceCallLogsInfo != null) {
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

    fun getDeviceSmSInfo(userId: String): Flow<IResult<DeviceSmSResponseModel?>> {
        return flow {
            emit(getDeviceSmSInfoApi(userId))
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun getDeviceSmSInfoApi(
        userId: String
    ): IResult<DeviceSmSResponseModel?> {
        val result = api.getDeviceSmS(userId)
        return when (result.status) {
            IResult.Status.SUCCESS -> {

                val addDeviceCallLogsInfo = result.data
                if (addDeviceCallLogsInfo != null) {
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

    fun getDeviceContactsInfo(userId: String): Flow<IResult<DeviceContactsResponseModel?>> {
        return flow {
            emit(getDeviceContactsInfoApi(userId))
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun getDeviceContactsInfoApi(
        userId: String
    ): IResult<DeviceContactsResponseModel?> {
        val result = api.getDeviceContacts(userId)
        return when (result.status) {
            IResult.Status.SUCCESS -> {

                val addDeviceCallLogsInfo = result.data
                if (addDeviceCallLogsInfo != null) {
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

    fun getDeviceAppsInfo(userId: String): Flow<IResult<DeviceAppsResponseModel?>> {
        return flow {
            emit(getDeviceAppsInfoApi(userId))
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun getDeviceAppsInfoApi(
        userId: String
    ): IResult<DeviceAppsResponseModel?> {
        val result = api.getDeviceApps(userId)
        return when (result.status) {
            IResult.Status.SUCCESS -> {

                val addDeviceCallLogsInfo = result.data
                if (addDeviceCallLogsInfo != null) {
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
