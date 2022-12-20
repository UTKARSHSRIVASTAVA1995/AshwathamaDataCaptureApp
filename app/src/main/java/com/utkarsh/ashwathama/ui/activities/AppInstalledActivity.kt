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
import com.utkarsh.ashwathama.databinding.ActivityDeviceAppsBinding
import com.utkarsh.ashwathama.ui.adapter.DeviceAppsListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppInstalledActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityDeviceAppsBinding
    private val viewModel: MainActivityViewModel by viewModels()
    lateinit var mRecyclerView: RecyclerView
    private val deviceCallLogsAdapter: DeviceAppsListAdapter = DeviceAppsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_device_apps)

        val userDetailsPref = getSharedPreferences("UserLoginDetails", MODE_PRIVATE)
        var userId = userDetailsPref.getInt("usermstId", 0)

        //Calling Device Call Logs Api Method Below
        getDeviceApps(userId.toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

    private fun getDeviceApps(userId: String) {

        viewModel.getDeviceApps(userId)
            .observe(this, androidx.lifecycle.Observer { result ->
                when (result.status) {
                    IResult.Status.SUCCESS -> {
                        mRecyclerView = mBinding.deviceAppsLogsRecyclerView
                        mRecyclerView.setHasFixedSize(true)
                        mRecyclerView.layoutManager = LinearLayoutManager(this)
                        result.data?.let { deviceCallLogsAdapter.RecyclerAdapter(it) }
                        mRecyclerView.adapter = deviceCallLogsAdapter
                        mBinding.pbDeviceApps.visibility = View.GONE

                    }
                    IResult.Status.ERROR -> {
                        result.message?.let {
                            Log.d(ContentValues.TAG, "Error: ${result.message}")
                            mBinding.pbDeviceApps.visibility = View.GONE
                        }
                    }
                    IResult.Status.LOADING -> {
                        Log.d(ContentValues.TAG, "Loading State")
                        mBinding.pbDeviceApps.visibility = View.GONE

                    }
                }
            })

    }
}