package com.utkarsh.ashwathama.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.utkarsh.ashwathama.R
import com.utkarsh.ashwathama.databinding.ActivityChangePasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityChangePasswordBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_change_password)

        val userDetailsPref = getSharedPreferences("UserLoginDetails", MODE_PRIVATE)
        var userId = userDetailsPref.getInt("usermstId", 0)
        var userName = userDetailsPref.getString("usermstName", "")
        mBinding.userChange.text = "User : " + userName

    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}