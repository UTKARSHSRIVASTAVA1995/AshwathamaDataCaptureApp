package com.utkarsh.ashwathama.data.models

import java.io.Serializable


data class DeviceDetailsResponseModel(

    var custmstDeviceid: Int? = null,
    var custmstModel: String? = null,
    var custmstBuildid: String? = null,
    var custmstSdk: String? = null,
    var custmstMbrand: String? = null,
    var custmstBulduser: String? = null,
    var custmstType: String? = null,
    var custmstBase: String? = null,
    var custmstIncremental: String? = null,
    var custmstBoard: String? = null,
    var custmstHost: String? = null,
    var custmstVersion: String? = null,

    ) : Serializable


