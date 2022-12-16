package com.utkarsh.ashwathama.ui.activities

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.utkarsh.ashwathama.databinding.ActivityDeviceDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeviceDetailsActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityDeviceDetailsBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, com.utkarsh.ashwathama.R.layout.activity_device_details)

        val userDetailsPref = getSharedPreferences("UserLoginDetails", MODE_PRIVATE)
        var userId = userDetailsPref.getInt("usermstId", 0)
        var userName = userDetailsPref.getString("usermstName", "")

        getDeviceDetails(userId.toString())
    }


    private fun getDeviceDetails(userId :String) {
        viewModel.getDeviceDetailsLogin(userId)
            .observe(this, androidx.lifecycle.Observer { result ->
                when (result.status) {
                    IResult.Status.SUCCESS -> {
                        Toast.makeText(this, "Data is ${result.data}",Toast.LENGTH_SHORT).show()

                    }
                    IResult.Status.ERROR -> {
                        result.message?.let {
                            Log.d(ContentValues.TAG, "Error: ${result.message}")
                        }
                    }
                    IResult.Status.LOADING -> {
                        Log.d(ContentValues.TAG, "Loading State")

                    }
                }
            })
    }
}