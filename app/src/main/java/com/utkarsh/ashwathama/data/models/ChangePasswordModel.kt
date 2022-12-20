package com.utkarsh.ashwathama.data.models

import java.io.Serializable


data class ChangePasswordModel(

    var name: String? = null,
    var password: String? = null,

    ) : Serializable


