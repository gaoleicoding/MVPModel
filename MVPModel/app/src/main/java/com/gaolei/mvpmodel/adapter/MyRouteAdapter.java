package com.gaolei.mvpmodel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.mmodel.FeedArticleData;

import java.util.List;

public class MyRouteAdapter extends RecyclerView.Adapter<MyRouteAdapter.MyViewHolder> {

    public Context context;
    int selectPosition = 0;
    OnItemClickListener listener;
    List<FeedArticleData> list;

    public MyRouteAdapter(Context context, List<FeedArticleData> list) {
        this.context = context;
        this.list = list;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_project_list, null);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = (int) view.getTag();
                if (listener != null) {
                    listener.onItemClick(view, position);
                }
            }
        });
        return holder;
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemView.setTag(position);
        FeedArticleData projectInfo=list.get(position);
        Log.d("gaolei","onBindViewHolder-----------"+position);
        Log.d("gaolei","projectInfo.getTitle()-----------"+projectInfo.getTitle());
        holder.item_project_list_title_tv.setText(projectInfo.getTitle());
        holder.item_project_list_content_tv.setText(projectInfo.getDesc());
        holder.item_project_list_time_tv.setText(projectInfo.getNiceDate());
        holder.item_project_list_author_tv.setText(projectInfo.getAuthor());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_project_list_title_tv,item_project_list_content_tv,item_project_list_time_tv,item_project_list_author_tv;

        public MyViewHolder(View view) {
            super(view);
            item_project_list_title_tv = (TextView) view.findViewById(R.id.item_project_list_title_tv);
            item_project_list_content_tv = (TextView) view.findViewById(R.id.item_project_list_content_tv);
            item_project_list_time_tv = (TextView) view.findViewById(R.id.item_project_list_time_tv);
            item_project_list_author_tv = (TextView) view.findViewById(R.id.item_project_list_author_tv);
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}