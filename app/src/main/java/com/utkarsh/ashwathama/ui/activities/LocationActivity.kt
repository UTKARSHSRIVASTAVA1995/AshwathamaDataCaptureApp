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
import com.utkarsh.ashwathama.R
import com.utkarsh.ashwathama.databinding.ActivityLocationBinding
import com.utkarsh.ashwathama.ui.adapter.DeviceLocationListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityLocationBinding
    private val viewModel: MainActivityViewModel by viewModels()
    lateinit var mRecyclerView: RecyclerView
    private val deviceLocationListAdapter: DeviceLocationListAdapter = DeviceLocationListAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_location)

        val userDetailsPref = getSharedPreferences("UserLoginDetails", MODE_PRIVATE)
        var userId = userDetailsPref.getInt("usermstId", 0)

        //Calling Device Details Api Method Below
        getDeviceLocation(userId.toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun getDeviceLocation(userId: String) {
        viewModel.getDeviceLocation(userId)
            .observe(this, androidx.lifecycle.Observer { result ->
                when (result.status) {
                    IResult.Status.SUCCESS -> {
                        mRecyclerView = mBinding.deviceLocationRecyclerView
                        mRecyclerView.setHasFixedSize(true)
                        mRecyclerView.layoutManager = LinearLayoutManager(this)
                        result.data?.let { deviceLocationListAdapter.RecyclerAdapter(it) }
                        mRecyclerView.adapter = deviceLocationListAdapter
                        mBinding.pbDeviceLocation.visibility = View.GONE

                    }
                    IResult.Status.ERROR -> {
                        result.message?.let {
                            Log.d(ContentValues.TAG, "Error: ${result.message}")
                            mBinding.pbDeviceLocation.visibility = View.GONE
                        }
                    }
                    IResult.Status.LOADING -> {
                        Log.d(ContentValues.TAG, "Loading State")
                        mBinding.pbDeviceLocation.visibility = View.GONE

                    }
                }
            })
    }
}