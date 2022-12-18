package com.utkarsh.ashwathama.ui.activities

import IResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.utkarsh.ashwathama.data.models.DeviceCallLogsResponseModel
import com.utkarsh.ashwathama.data.models.DeviceDetailsListResponseModel
import com.utkarsh.ashwathama.data.models.DeviceDetailsResponseModel
import com.utkarsh.ashwathama.data.models.LoginResponseModel
import com.utkarsh.ashwathama.data.repository.MainActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val mainActivityRepository: MainActivityRepository,
) : ViewModel() {
    lateinit var userLogin: LiveData<IResult<LoginResponseModel?>>

    fun getUserLogin(userId: String, passWord: String): LiveData<IResult<LoginResponseModel?>> {
        userLogin = mainActivityRepository.getUserLoginInfo(userId, passWord)
            .onStart { emit(IResult.loading()) }
            .asLiveData()
        return userLogin
    }

    fun getDeviceDetailsLogin(userId: String): LiveData<IResult<DeviceDetailsListResponseModel?>> {
      return  mainActivityRepository.getDeviceDetailsInfo(userId)
            .onStart { emit(IResult.loading()) }
            .asLiveData() as LiveData<IResult<DeviceDetailsListResponseModel?>>
    }

    fun getDeviceCallLogs(userId: String): LiveData<IResult<DeviceCallLogsResponseModel?>> {
        return  mainActivityRepository.getDeviceCallLogsInfo(userId)
            .onStart { emit(IResult.loading()) }
            .asLiveData() as LiveData<IResult<DeviceCallLogsResponseModel?>>

    }

}
