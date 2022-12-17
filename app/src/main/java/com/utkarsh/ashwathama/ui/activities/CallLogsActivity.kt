package com.utkarsh.ashwathama.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.utkarsh.ashwathama.R
import com.utkarsh.ashwathama.databinding.ActivityCallLogsBinding

class CallLogsActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityCallLogsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_call_logs)
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}