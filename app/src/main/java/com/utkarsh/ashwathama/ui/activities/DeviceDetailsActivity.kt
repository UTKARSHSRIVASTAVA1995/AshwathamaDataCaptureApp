package com.utkarsh.ashwathama.ui.activities

import IResult
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utkarsh.ashwathama.databinding.ActivityDeviceDetailsBinding
import com.utkarsh.ashwathama.ui.adapter.DeviceDetailsListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DeviceDetailsActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityDeviceDetailsBinding
    private val viewModel: MainActivityViewModel by viewModels()
    lateinit var mRecyclerView: RecyclerView
    private val deviceDetailsListAdapter: DeviceDetailsListAdapter = DeviceDetailsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, com.utkarsh.ashwathama.R.layout.activity_device_details)

        val userDetailsPref = getSharedPreferences("UserLoginDetails", MODE_PRIVATE)
        var userId = userDetailsPref.getInt("usermstId", 0)

        //Calling Device Details Api Method Below
        getDeviceDetails(userId.toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun getDeviceDetails(userId: String) {
        viewModel.getDeviceDetailsLogin(userId)
            .observe(this, androidx.lifecycle.Observer { result ->
                when (result.status) {
                    IResult.Status.SUCCESS -> {
                        mRecyclerView = mBinding.deviceListRecyclerView
                        mRecyclerView.setHasFixedSize(true)
                        mRecyclerView.layoutManager = LinearLayoutManager(this)
                        result.data?.let { deviceDetailsListAdapter.RecyclerAdapter(it) }
                        mRecyclerView.adapter = deviceDetailsListAdapter
                        mBinding.pbDeviceDetails.visibility = View.GONE

                    }
                    IResult.Status.ERROR -> {
                        result.message?.let {
                            Log.d(ContentValues.TAG, "Error: ${result.message}")
                            mBinding.pbDeviceDetails.visibility = View.GONE
                        }
                    }
                    IResult.Status.LOADING -> {
                        Log.d(ContentValues.TAG, "Loading State")
                        mBinding.pbDeviceDetails.visibility = View.GONE

                    }
                }
            })
    }
}