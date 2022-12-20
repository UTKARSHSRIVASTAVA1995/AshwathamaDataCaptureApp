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
import androidx.lifecycle.MutableLiveData
import com.utkarsh.ashwathama.R
import com.utkarsh.ashwathama.data.models.ChangePasswordModel
import com.utkarsh.ashwathama.databinding.ActivityChangePasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityChangePasswordBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private val addNewPwdLogModel = MutableLiveData<ChangePasswordModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_change_password)

        val userDetailsPref = getSharedPreferences("UserLoginDetails", MODE_PRIVATE)
        var userId = userDetailsPref.getInt("usermstId", 0)
        var userName = userDetailsPref.getString("usermstName", "")
        mBinding.userChange.text = "User : " + userName

        if (userName != null) {
            clickListener(userName)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun clickListener(userName: String) {

        mBinding.btnConfirm.setOnClickListener {
            mBinding.pbChangePassword.visibility = View.VISIBLE
            addNewPassword(userName)
        }
    }

    // Below is Live Data Of Location Details
    private fun updateNewPasswordModelData(addNewPasswordInfo: ChangePasswordModel) {
        addNewPwdLogModel.postValue(addNewPasswordInfo)
    }

    private fun fetchNewPasswordModelData(): ChangePasswordModel? {
        return addNewPwdLogModel.value
    }

    private fun addNewPassword(userName: String) {

        val newPassword = mBinding.etNewPassword.text.toString()

        var addNewPwdLogModel = fetchNewPasswordModelData()
        addNewPwdLogModel = ChangePasswordModel()
        addNewPwdLogModel?.name = userName.toString()
        addNewPwdLogModel?.password = newPassword.toString()

        updateNewPasswordModelData(addNewPwdLogModel)

        viewModel.changeUserPassword(addNewPwdLogModel)
            .observe(this, androidx.lifecycle.Observer { result ->
                when (result.status) {
                    IResult.Status.SUCCESS -> {

                        mBinding.pbChangePassword.visibility = View.GONE

                        val intent = Intent(this@ChangePasswordActivity, LoginActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        finish()
                        Toast.makeText(this, "Password Change Successfully", Toast.LENGTH_SHORT)
                            .show()
                        Toast.makeText(
                            this,
                            "Please Login Again With new Password",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    IResult.Status.ERROR -> {
                        mBinding.pbChangePassword.visibility = View.GONE
                        result.message?.let {
                            Log.d(ContentValues.TAG, "Error: ${result.message}")
                        }
                    }
                    IResult.Status.LOADING -> {
                        mBinding.pbChangePassword.visibility = View.GONE
                        Log.d(ContentValues.TAG, "Loading State")

                    }
                }
            })
    }
}