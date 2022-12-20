package com.utkarsh.ashwathama.network.interfaces

import com.utkarsh.ashwathama.data.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MainActivityInterface {

    @POST("ChangePassword")
    suspend fun changePassword(@Body changePasswordModel: ChangePasswordModel): Response<Boolean>

    @GET("GetUserLogin/{user_Id}/{user_pwd}")
    suspend fun getUserLogin(@Path("user_Id") userId: String, @Path("user_pwd") passWord: String): Response<LoginResponseModel>

    @GET("GetCustmst/{user_Id}")
    suspend fun getDeviceDetails(@Path("user_Id") userId: String): Response<DeviceDetailsListResponseModel>

    @GET("GetCustCall/{user_Id}")
    suspend fun getDeviceCallLogs(@Path("user_Id") userId: String): Response<DeviceCallLogsResponseModel>

    @GET("GetCustLocation/{user_Id}")
    suspend fun getDeviceLocation(@Path("user_Id") userId: String): Response<DeviceLocationResponseModel>

    @GET("GetCustSms/{user_Id}")
    suspend fun getDeviceSmS(@Path("user_Id") userId: String): Response<DeviceSmSResponseModel>

    @GET("GetCustContact/{user_Id}")
    suspend fun getDeviceContacts(@Path("user_Id") userId: String): Response<DeviceContactsResponseModel>

    @GET("GetCustApp/{user_Id}")
    suspend fun getDeviceAppInstalled(@Path("user_Id") userId: String): Response<DeviceAppsResponseModel>

}