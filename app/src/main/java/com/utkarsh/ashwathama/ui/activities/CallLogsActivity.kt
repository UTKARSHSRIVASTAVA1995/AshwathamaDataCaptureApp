package com.utkarsh.ashwathama.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.utkarsh.ashwathama.R

class CallLogsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_logs)
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}