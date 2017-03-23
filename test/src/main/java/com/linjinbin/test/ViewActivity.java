package com.linjinbin.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by binge on 2017/3/22.
 */
public class ViewActivity extends Activity {

    private RecyclerView mRecycler;
    private ViewAdapter mAdapter;
    private List<String> list_data = new ArrayList<>();

    private List<List<AppStart>> data = new ArrayList<>();
//    private StringBuffer sb = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        initView();

        for (int i = 0; i < getCod().size(); i++) {
            StringBuffer sb = new StringBuffer();
            sb.append(getCod().get(i).get(0).getTime() + "\n");
            for (int j = 0; j < getCod().get(i).size(); j++) {
                sb.append("("+getCod().get(i).get(j).getX() + ","
                        + getCod().get(i).get(j).getY() + ")"+" ");
            }

            list_data.add(sb.toString());
        }


//        toast(sb.toString());

        mAdapter = new ViewAdapter(this, list_data);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ViewAdapter.onClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {

            }
        });
    }

    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.view_rl);
    }


    public List<CreateTime> getData() {
        List<CreateTime> list_time = DataSupport.findAll(CreateTime.class);
        return list_time;
    }

    public List<List<Coordinate>> getCod() {
        List<List<Coordinate>> list_cod = new ArrayList<>();
        for (int i = 0; i < getData().size(); i++) {
            list_cod.add(DataSupport.where("time = ?", getData().get(i).getTime())
                    .find(Coordinate.class));
        }
        return list_cod;
    }


    public void toast(List<CreateTime> list_time) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list_time.size(); i++) {
            sb.append(list_time.get(i).getTime() + "\n");
        }
        Toast.makeText(ViewActivity.this, sb, Toast.LENGTH_SHORT).show();
    }

    public void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}
