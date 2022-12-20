package com.utkarsh.ashwathama.ui.activities

import IResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.utkarsh.ashwathama.data.models.*
import com.utkarsh.ashwathama.data.repository.MainActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val mainActivityRepository: MainActivityRepository,
) : ViewModel() {
    lateinit var userLogin: LiveData<IResult<LoginResponseModel?>>

    fun changeUserPassword(changePasswordModel: ChangePasswordModel): LiveData<IResult<Boolean?>> {
        return mainActivityRepository.changePasswordInfo(changePasswordModel)
            .onStart { emit(IResult.loading()) }
            .asLiveData() as LiveData<IResult<Boolean?>>
    }

    fun getUserLogin(userId: String, passWord: String): LiveData<IResult<LoginResponseModel?>> {
        userLogin = mainActivityRepository.getUserLoginInfo(userId, passWord)
            .onStart { emit(IResult.loading()) }
            .asLiveData()
        return userLogin
    }

    fun getDeviceDetailsLogin(userId: String): LiveData<IResult<DeviceDetailsListResponseModel?>> {
        return mainActivityRepository.getDeviceDetailsInfo(userId)
            .onStart { emit(IResult.loading()) }
            .asLiveData() as LiveData<IResult<DeviceDetailsListResponseModel?>>
    }

    fun getDeviceCallLogs(userId: String): LiveData<IResult<DeviceCallLogsResponseModel?>> {
        return mainActivityRepository.getDeviceCallLogsInfo(userId)
            .onStart { emit(IResult.loading()) }
            .asLiveData() as LiveData<IResult<DeviceCallLogsResponseModel?>>
    }

    fun getDeviceLocation(userId: String): LiveData<IResult<DeviceLocationResponseModel?>> {
        return mainActivityRepository.getDeviceLocationInfo(userId)
            .onStart { emit(IResult.loading()) }
            .asLiveData() as LiveData<IResult<DeviceLocationResponseModel?>>
    }

    fun getDeviceSmS(userId: String): LiveData<IResult<DeviceSmSResponseModel?>> {
        return mainActivityRepository.getDeviceSmSInfo(userId)
            .onStart { emit(IResult.loading()) }
            .asLiveData() as LiveData<IResult<DeviceSmSResponseModel?>>
    }

    fun getDeviceContacts(userId: String): LiveData<IResult<DeviceContactsResponseModel?>> {
        return mainActivityRepository.getDeviceContactsInfo(userId)
            .onStart { emit(IResult.loading()) }
            .asLiveData() as LiveData<IResult<DeviceContactsResponseModel?>>
    }

    fun getDeviceApps(userId: String): LiveData<IResult<DeviceAppsResponseModel?>> {
        return mainActivityRepository.getDeviceAppsInfo(userId)
            .onStart { emit(IResult.loading()) }
            .asLiveData() as LiveData<IResult<DeviceAppsResponseModel?>>
    }
}
