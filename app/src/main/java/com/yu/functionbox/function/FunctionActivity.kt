package com.yu.functionbox.function

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.yu.functionbox.R
import com.yu.functionbox.databinding.ActivityFunctionBinding

class FunctionActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityFunctionBinding
    private lateinit var mViewModel: FunctionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_function)
        mViewModel = FunctionViewModel(this)
        mBinding.viewModel = mViewModel
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        toolbar.setNavigationOnClickListener { finish() }

        initData()
    }

    private fun initData() {
        val intent = intent
        when (intent.getStringExtra(KEY_TYPE)) {
            FLAG_CREATE_NEW -> mViewModel.enableEdit(true)
            FLAG_CHECK -> {
                val content = intent.getStringExtra(KEY_CONTENT)
                val id = intent.getLongExtra(KEY_ID, 0L)
                mViewModel.setContent(id, content)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun finish() {
        if (mViewModel.isEdited) {
            this.setResult(RESULT_FUNCTION)
        }
        super.finish()
    }

    companion object {
        val FLAG_CREATE_NEW = "flag_create_new"
        val FLAG_CHECK = "flag_check"
        val KEY_CONTENT = "key_content"
        val KEY_ID = "key_id"
        val KEY_TYPE = "key_type"
        val RESULT_FUNCTION = 101
        val REQUEST_FUNCTION = 1001
    }
}
