package com.yu.functionbox.function;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.yu.functionbox.R;
import com.yu.functionbox.databinding.ActivityFunctionBinding;

public class FunctionActivity extends AppCompatActivity {
    private ActivityFunctionBinding mBinding;
    private FunctionViewModel mViewModel;
    public static final String FLAG_CREATE_NEW = "flag_create_new";
    public static final String FLAG_CHECK = "flag_check";
    public static final String KEY_CONTENT = "key_content";
    public static final String KEY_ID = "key_id";
    public static final String KEY_TYPE = "key_type";
    public static final int RESULT_FUNCTION = 101;
    public final static int REQUEST_FUNCTION = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_function);
        mViewModel = new FunctionViewModel(this);
        mBinding.setViewModel(mViewModel);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        switch (intent.getStringExtra(KEY_TYPE)){
            case FLAG_CREATE_NEW:
                mViewModel.enableEdit(true);
                break;
            case FLAG_CHECK:
                String content = intent.getStringExtra(KEY_CONTENT);
                long id = intent.getLongExtra(KEY_ID,0L);
                mViewModel.setContent(id,content);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void finish() {
        if(mViewModel.isEdited()){
            this.setResult(RESULT_FUNCTION);
        }
        super.finish();
    }
}
