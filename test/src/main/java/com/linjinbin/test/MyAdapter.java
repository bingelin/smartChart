package com.linjinbin.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by binge on 2017/3/19.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> list_x = new ArrayList<>();
    private List<String> list_y = new ArrayList<>();
    private ItemClickListener listener;



    interface ItemClickListener{
        void onItemClickListener (View v,int position);
    }


    public MyAdapter(Context context, List<String> x,List<String> y ) {
        mContext = context;
        this.list_x = x;
        this.list_y = y;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_x.setText(list_x.get(position));
        holder.tv_y.setText(list_y.get(position));
        holder.llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClickListener(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_x.size();
    }

    public void addItem(String x, String y) {
        list_x.add(x);
        list_y.add(y);
        this.notifyItemInserted(this.getItemCount());
    }


    public void delectItem(int position) {
        list_x.remove(position);
        list_y.remove(position);
        this.notifyDataSetChanged();
    }

    public void removeAll() {
        if (list_x.size() > 0) {
            this.notifyItemRangeRemoved(0, getItemCount());
        }
    }


    public void setOnItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_x;
        TextView tv_y;
        LinearLayout llayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            llayout = (LinearLayout) itemView.findViewById(R.id.item_llayout);
            tv_x = (TextView) itemView.findViewById(R.id.item_tv_x);
            tv_y = (TextView) itemView.findViewById(R.id.item_tv_y);
        }
    }

}
