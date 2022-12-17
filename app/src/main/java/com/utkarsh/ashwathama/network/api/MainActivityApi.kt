package com.utkarsh.ashwathama.network.api

import IResult
import com.utkarsh.ashwathama.data.models.DeviceDetailsListResponseModel
import com.utkarsh.ashwathama.data.models.LoginResponseModel
import com.utkarsh.ashwathama.network.NetworkService
import com.utkarsh.ashwathama.network.interfaces.MainActivityInterface
import handleApiResponse
import javax.inject.Inject

class MainActivityApi @Inject constructor(
    private val networkService: NetworkService
) {
    suspend fun getUserLogin(userId: String, passWord: String): IResult<LoginResponseModel?> {
        val getUserLoginService = networkService.build(MainActivityInterface::class.java)
        return handleApiResponse(request = { getUserLoginService.getUserLogin(userId, passWord) })
    }


    suspend fun getDeviceDetails(userId: String): IResult<DeviceDetailsListResponseModel?> {
        val getDeviceDetailsService = networkService.build(MainActivityInterface::class.java)
        return handleApiResponse(request = { getDeviceDetailsService.getDeviceDetails(userId) })
    }

}