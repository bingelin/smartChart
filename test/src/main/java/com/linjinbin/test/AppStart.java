package com.linjinbin.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;

/**
 * Created by binge on 2017/3/22.
 */

public class AppStart extends Activity implements View.OnClickListener{
    private Button bt_add;
    private Button bt_view;
    private Button bt_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appstart);
        initView();
    }

    private void initView() {
        bt_add = (Button) findViewById(R.id.appstart_bt_add);
        bt_view = (Button) findViewById(R.id.appstart_bt_view);
        bt_delete = (Button) findViewById(R.id.appstart_bt_delete);
        bt_add.setOnClickListener(this);
        bt_view.setOnClickListener(this);
        bt_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appstart_bt_add:
                startActivity(MainActivity.class);
                break;
            case R.id.appstart_bt_view:
                startActivity(ViewActivity.class);
                break;
            case R.id.appstart_bt_delete:
                DataSupport.deleteAll(Coordinate.class);
                DataSupport.deleteAll(CreateTime.class);
                break;
        }
    }

    public void startActivity(Class<?> mClass) {
        Intent i = new Intent(AppStart.this, mClass);
        startActivity(i);
    }
}
