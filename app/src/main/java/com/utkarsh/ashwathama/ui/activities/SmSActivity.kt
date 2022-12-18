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
import com.utkarsh.ashwathama.databinding.ActivitySmSactivityBinding
import com.utkarsh.ashwathama.ui.adapter.DeviceLocationListAdapter
import com.utkarsh.ashwathama.ui.adapter.DeviceLocationSmSAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SmSActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivitySmSactivityBinding
    private val viewModel: MainActivityViewModel by viewModels()
    lateinit var mRecyclerView: RecyclerView
    private val deviceSmSListAdapter: DeviceLocationSmSAdapter = DeviceLocationSmSAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sm_sactivity)

        val userDetailsPref = getSharedPreferences("UserLoginDetails", MODE_PRIVATE)
        var userId = userDetailsPref.getInt("usermstId", 0)

        //Calling Device Details Api Method Below
        getDeviceSmS(userId.toString())

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun getDeviceSmS(userId: String) {
        viewModel.getDeviceSmS(userId)
            .observe(this, androidx.lifecycle.Observer { result ->
                when (result.status) {
                    IResult.Status.SUCCESS -> {
                        mRecyclerView = mBinding.deviceSmSRecyclerView
                        mRecyclerView.setHasFixedSize(true)
                        mRecyclerView.layoutManager = LinearLayoutManager(this)
                        result.data?.let { deviceSmSListAdapter.RecyclerAdapter(it) }
                        mRecyclerView.adapter = deviceSmSListAdapter
                        mBinding.pbDeviceSmS.visibility = View.GONE

                    }
                    IResult.Status.ERROR -> {
                        result.message?.let {
                            Log.d(ContentValues.TAG, "Error: ${result.message}")
                            mBinding.pbDeviceSmS.visibility = View.GONE
                        }
                    }
                    IResult.Status.LOADING -> {
                        Log.d(ContentValues.TAG, "Loading State")
                        mBinding.pbDeviceSmS.visibility = View.GONE

                    }
                }
            })
    }
}