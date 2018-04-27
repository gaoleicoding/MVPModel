package com.gaolei.mvpmodel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.mmodel.ProjectInfo;

import java.util.List;

public class MyRouteAdapter extends RecyclerView.Adapter<MyRouteAdapter.MyViewHolder> {

    public Context context;
    int selectPosition = 0;
    OnItemClickListener listener;
    List<ProjectInfo> list;

    public MyRouteAdapter(Context context, List<ProjectInfo> list) {
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
        ProjectInfo projectInfo=list.get(position);
        holder.item_project_list_title_tv.setText(projectInfo.title);
        holder.item_project_list_content_tv.setText(projectInfo.desc);
        holder.item_project_list_time_tv.setText(projectInfo.title);
        holder.item_project_list_author_tv.setText(projectInfo.author);
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