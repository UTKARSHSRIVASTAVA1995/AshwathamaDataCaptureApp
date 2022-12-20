package com.utkarsh.ashwathama.network.api

import IResult
import com.utkarsh.ashwathama.data.models.*
import com.utkarsh.ashwathama.network.NetworkService
import com.utkarsh.ashwathama.network.interfaces.MainActivityInterface
import handleApiResponse
import javax.inject.Inject

class MainActivityApi @Inject constructor(
    private val networkService: NetworkService
) {

    suspend fun changePassword(changePasswordModel: ChangePasswordModel): IResult<Boolean?> {
        val getUserLoginService = networkService.build(MainActivityInterface::class.java)
        return handleApiResponse(request = { getUserLoginService.changePassword(changePasswordModel) })
    }

    suspend fun getUserLogin(userId: String, passWord: String): IResult<LoginResponseModel?> {
        val getUserLoginService = networkService.build(MainActivityInterface::class.java)
        return handleApiResponse(request = { getUserLoginService.getUserLogin(userId, passWord) })
    }

    suspend fun getDeviceDetails(userId: String): IResult<DeviceDetailsListResponseModel?> {
        val getDeviceDetailsService = networkService.build(MainActivityInterface::class.java)
        return handleApiResponse(request = { getDeviceDetailsService.getDeviceDetails(userId) })
    }

    suspend fun getDeviceCallLogs(userId: String): IResult<DeviceCallLogsResponseModel?> {
        val getDeviceCallLogsService = networkService.build(MainActivityInterface::class.java)
        return handleApiResponse(request = { getDeviceCallLogsService.getDeviceCallLogs(userId) })
    }

    suspend fun getDeviceLocation(userId: String): IResult<DeviceLocationResponseModel?> {
        val getDeviceLocationService = networkService.build(MainActivityInterface::class.java)
        return handleApiResponse(request = { getDeviceLocationService.getDeviceLocation(userId) })
    }

    suspend fun getDeviceSmS(userId: String): IResult<DeviceSmSResponseModel?> {
        val getDeviceSmSService = networkService.build(MainActivityInterface::class.java)
        return handleApiResponse(request = { getDeviceSmSService.getDeviceSmS(userId) })
    }

    suspend fun getDeviceContacts(userId: String): IResult<DeviceContactsResponseModel?> {
        val getDeviceContactsService = networkService.build(MainActivityInterface::class.java)
        return handleApiResponse(request = { getDeviceContactsService.getDeviceContacts(userId) })
    }

    suspend fun getDeviceApps(userId: String): IResult<DeviceAppsResponseModel?> {
        val getDeviceAppsService = networkService.build(MainActivityInterface::class.java)
        return handleApiResponse(request = { getDeviceAppsService.getDeviceAppInstalled(userId) })
    }
}