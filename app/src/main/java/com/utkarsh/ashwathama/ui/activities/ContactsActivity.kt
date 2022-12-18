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
import com.utkarsh.ashwathama.databinding.ActivityContactsBinding
import com.utkarsh.ashwathama.ui.adapter.DeviceContactsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactsActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityContactsBinding
    private val viewModel: MainActivityViewModel by viewModels()
    lateinit var mRecyclerView: RecyclerView
    private val deviceContactsListAdapter: DeviceContactsAdapter = DeviceContactsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_contacts)

        val userDetailsPref = getSharedPreferences("UserLoginDetails", MODE_PRIVATE)
        var userId = userDetailsPref.getInt("usermstId", 0)

        //Calling Contacts Api Method Below
        getDeviceContacts(userId.toString())
    }

    private fun getDeviceContacts(userId: String) {
        viewModel.getDeviceContacts(userId)
            .observe(this, androidx.lifecycle.Observer { result ->
                when (result.status) {
                    IResult.Status.SUCCESS -> {
                        mRecyclerView = mBinding.deviceContactsRecyclerView
                        mRecyclerView.setHasFixedSize(true)
                        mRecyclerView.layoutManager = LinearLayoutManager(this)
                        result.data?.let { deviceContactsListAdapter.RecyclerAdapter(it) }
                        mRecyclerView.adapter = deviceContactsListAdapter
                        mBinding.pbDeviceConacts.visibility = View.GONE

                    }
                    IResult.Status.ERROR -> {
                        result.message?.let {
                            Log.d(ContentValues.TAG, "Error: ${result.message}")
                            mBinding.pbDeviceConacts.visibility = View.GONE
                        }
                    }
                    IResult.Status.LOADING -> {
                        Log.d(ContentValues.TAG, "Loading State")
                        mBinding.pbDeviceConacts.visibility = View.GONE

                    }
                }
            })
    }
}