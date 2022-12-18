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
import com.utkarsh.ashwathama.databinding.ActivityCallLogsBinding
import com.utkarsh.ashwathama.ui.adapter.DeviceCallLogsListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CallLogsActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityCallLogsBinding
    private val viewModel: MainActivityViewModel by viewModels()
    lateinit var mRecyclerView: RecyclerView
    private val deviceCallLogsAdapter: DeviceCallLogsListAdapter = DeviceCallLogsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_call_logs)

        val userDetailsPref = getSharedPreferences("UserLoginDetails", MODE_PRIVATE)
        var userId = userDetailsPref.getInt("usermstId", 0)

        //Calling Device Call Logs Api Method Below
        getDeviceCallLogs(userId.toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

    private fun getDeviceCallLogs(userId: String) {

        viewModel.getDeviceCallLogs(userId)
            .observe(this, androidx.lifecycle.Observer { result ->
                when (result.status) {
                    IResult.Status.SUCCESS -> {
                        mRecyclerView = mBinding.deviceCallLogsRecyclerView
                        mRecyclerView.setHasFixedSize(true)
                        mRecyclerView.layoutManager = LinearLayoutManager(this)
                        result.data?.let { deviceCallLogsAdapter.RecyclerAdapter(it) }
                        mRecyclerView.adapter = deviceCallLogsAdapter
                        mBinding.pbDeviceCallLogs.visibility = View.GONE

                    }
                    IResult.Status.ERROR -> {
                        result.message?.let {
                            Log.d(ContentValues.TAG, "Error: ${result.message}")
                            mBinding.pbDeviceCallLogs.visibility = View.GONE
                        }
                    }
                    IResult.Status.LOADING -> {
                        Log.d(ContentValues.TAG, "Loading State")
                        mBinding.pbDeviceCallLogs.visibility = View.GONE

                    }
                }
            })

    }
}