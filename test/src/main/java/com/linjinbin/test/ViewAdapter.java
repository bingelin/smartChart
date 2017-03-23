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
 * Created by binge on 2017/3/23.
 */

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.CusViewHolder>{

    private Context mContext;
    private List<String> data = new ArrayList<>();
    private onClickListener listener;

    public ViewAdapter(Context mContext, List<String> data) {
        this.mContext = mContext;
        this.data = data;
    }

    public interface onClickListener {
        void onItemClickListener(View view,int position);
    }


    @Override
    public CusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view, parent, false);
        return new CusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CusViewHolder holder, final int position) {
        holder.llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClickListener(v, position);
            }
        });
        holder.tv.setText(data.get(position));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CusViewHolder extends RecyclerView.ViewHolder{
        LinearLayout llayout;
        TextView tv;

        public CusViewHolder(View itemView) {
            super(itemView);
            llayout = (LinearLayout) itemView.findViewById(R.id.item_view_ll);
            tv = (TextView) itemView.findViewById(R.id.item_view_tv);
        }
    }

    public void setOnItemClickListener(onClickListener listener) {
        this.listener = listener;
    }
}
