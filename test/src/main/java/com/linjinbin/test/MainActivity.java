package com.linjinbin.test;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button bt_add;
    private RecyclerView mRecycler;
    private MyAdapter myAdapter;
    private List<String> list_x;
    private List<String> list_y;
    private String str_x;
    private String str_y;

    private EditText et_x;
    private EditText et_y;

    private SQLiteDatabase db = LitePal.getDatabase();

    private String str_time ;

    private boolean isFirst = true; //判断是否是第一次进入并且成功创建表

    private List<Coordinate> queryData = new ArrayList<>();


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();



        myAdapter = new MyAdapter(this, list_x, list_y);
        mRecycler.setAdapter(myAdapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_x = et_x.getText().toString();
                str_y = et_y.getText().toString();
                if (isAllEmpty(str_x, str_y)) {
                    Toast.makeText(MainActivity.this, "x、y值不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (isEmpty(str_x)) {
                        Toast.makeText(MainActivity.this, "x值不能为空", Toast.LENGTH_SHORT).show();
                    } else if (isEmpty(str_y)) {
                        Toast.makeText(MainActivity.this, "y值不能为空", Toast.LENGTH_SHORT).show();
                    } else if (!isAllEmpty(str_x, str_y)) {

                        if (isFirst) {
                            str_time = getSystemTime();
                            Toast.makeText(MainActivity.this, str_time, Toast.LENGTH_SHORT).show();
                            CreateTime createTime = new CreateTime();
                            createTime.setTime(str_time);
                            createTime.save();
                            isFirst = false;
                        }


                        Coordinate coordinate = new Coordinate();
                        coordinate.setX(str_x);
                        coordinate.setY(str_y);
                        coordinate.setTime(str_time);
                        coordinate.saveThrows();
//                        if (coordinate.save()) {
//                            Toast.makeText(MainActivity.this, "创建成功", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(MainActivity.this, "创建不成功", Toast.LENGTH_SHORT).show();
//                        }
                        
                        myAdapter.addItem(str_x, str_y);


                    }
                }
            }
        });


        myAdapter.setOnItemClickListener(new MyAdapter.ItemClickListener() {
            @Override
            public void onItemClickListener(View v, final int position) {
                 new AlertDialog.Builder(MainActivity.this).setTitle("确定删除信息？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                myAdapter.delectItem(position);
                                DataSupport.delete(Coordinate.class, position + 1);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                queryData = DataSupport.findAll(Coordinate.class);
                                toast(queryData);
                            }
                        }).show();
            }
        });

    }

    public void toast(List<Coordinate> list_time) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list_time.size(); i++) {
            sb.append(list_time.get(i).getX() + "\n");
        }
        Toast.makeText(MainActivity.this, sb, Toast.LENGTH_SHORT).show();
    }

    private String getSystemTime() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }

    private void initView() {
        bt_add = (Button) findViewById(R.id.main_bt_add);
        mRecycler = (RecyclerView) findViewById(R.id.main_rv);
        et_x = (EditText) findViewById(R.id.main_et_x);
        et_y = (EditText) findViewById(R.id.main_et_y);

        list_x = new ArrayList<>();
        list_y = new ArrayList<>();




//        List<Coordinate> list = findAll(Coordinate.class);
//
//        for (int i = 0; i < list.size(); i++) {
//            list_x.add(list.get(i).getX());
//            list_y.add(list.get(i).getY());
//        }

    }

    public boolean isAllEmpty(String x, String y) {
        return isEmpty(x) && isEmpty(y);
    }

    public boolean isEmpty(String s) {
        if (s==null||s.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myAdapter.removeAll();
    }
}
