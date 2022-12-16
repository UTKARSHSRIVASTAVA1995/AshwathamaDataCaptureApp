package com.utkarsh.ashwathama.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.utkarsh.ashwathama.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, com.utkarsh.ashwathama.R.layout.activity_main)

        val userDetailsPref = getSharedPreferences("UserLoginDetails", MODE_PRIVATE)
        var userId = userDetailsPref.getInt("usermstId", 0)
        var userName = userDetailsPref.getString("usermstName", "")

        clickListener()
        // Setting UserName to UI
        if (userName != null) {
            setUserName(userName)
        }
    }

    private fun setUserName(userName: String) {

        mBinding.tvUserName.text = "Welcome " + userName
        mBinding.tvUserAswathama.text = userName + " Aswathama"
    }

    private fun clickListener() {

        mBinding.clDeviceDetails.setOnClickListener {
            val intent = Intent(this@MainActivity, DeviceDetailsActivity::class.java)
            startActivity(intent)
        }

        mBinding.deviceCallLogs.setOnClickListener {
            val intent = Intent(this@MainActivity, CallLogsActivity::class.java)
            startActivity(intent)
        }

        mBinding.btnLogout.setOnClickListener {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Logout")
            builder.setMessage("Are you sure you want to Logout ?")

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
                Toast.makeText(this, "Successfully Logged Out", Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                dialog.dismiss()
            }
            builder.show()
        }
    }
}