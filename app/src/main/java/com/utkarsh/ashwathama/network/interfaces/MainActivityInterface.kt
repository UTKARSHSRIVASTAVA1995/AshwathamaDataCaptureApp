package com.utkarsh.ashwathama.network.interfaces

import com.utkarsh.ashwathama.data.models.LoginResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MainActivityInterface {

    @GET("GetUserLogin/{user_Id}/{user_pwd}")
    suspend fun getUserLogin(@Path("user_Id") userId: String, @Path("user_pwd") passWord: String): Response<LoginResponseModel>

    @GET("GetUserLogin/{user_Id}/{user_pwd}")
    suspend fun getDeviceDetails(@Path("user_Id") userId: String, @Path("user_pwd") passWord: String): Response<LoginResponseModel>

}