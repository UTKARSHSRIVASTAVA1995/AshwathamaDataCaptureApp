package com.utkarsh.ashwathama.ui.activities

import IResult
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.utkarsh.ashwathama.R
import com.utkarsh.ashwathama.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityLoginBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        //Calling Method here to successfully Login
        mBinding.btnLogin.setOnClickListener {

            if (mBinding.etMail.text.isNullOrEmpty() && mBinding.etPassword.text.isNullOrEmpty()) {
                Toast.makeText(this, "Enter Email Id & Password", Toast.LENGTH_SHORT).show()
            } else {
                mBinding.pbLogin.visibility = View.VISIBLE
                getUserLogin()
            }
        }
    }

    private fun getUserLogin() {

        var email = mBinding.etMail.text.toString()
        var password = mBinding.etPassword.text.toString()

        viewModel.getUserLogin(email, password)
            .observe(this, androidx.lifecycle.Observer { result ->
                when (result.status) {
                    IResult.Status.SUCCESS -> {

                        if (result.data?.usermstName.equals(null)) {
                            Toast.makeText(
                                this,
                                "Enter Correct Login Credentials",
                                Toast.LENGTH_SHORT
                            ).show()
                            mBinding.pbLogin.visibility = View.GONE
                        } else {
                            mBinding.pbLogin.visibility = View.GONE
                            Log.d(ContentValues.TAG, "Success: ${result.data}")
                            val sharedPreferences =
                                getSharedPreferences("UserLoginDetails", MODE_PRIVATE)
                            val userDetailsPref = sharedPreferences.edit()
                            result.data?.usermstId?.let { userDetailsPref.putInt("usermstId", it) }
                            userDetailsPref.putString("usermstName", result.data?.usermstName)
                            userDetailsPref.commit()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                            Toast.makeText(this, "Successfully Logged in", Toast.LENGTH_SHORT)
                                .show()
                        }

                    }
                    IResult.Status.ERROR -> {
                        result.message?.let {
                            Log.d(ContentValues.TAG, "Error: ${result.message}")
                        }
                    }
                    IResult.Status.LOADING -> {
                        Log.d(ContentValues.TAG, "Loading State")

                    }
                }
            })
    }
}

