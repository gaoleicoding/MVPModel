package com.gaolei.mvpmodel.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.databinding.ItemProjectListBinding;
import com.gaolei.mvpmodel.mmodel.ProjectListData.FeedArticleData;

import java.util.List;

import static com.gaolei.mvpmodel.application.CustomApplication.options;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.MyViewHolder> {

    public Context context;
    OnItemClickListener listener;
    List<FeedArticleData> list;

    public ProjectAdapter(Context context, List<FeedArticleData> list) {
        this.context = context;
        this.list = list;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_project_list, null);
        ItemProjectListBinding bindView = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_project_list, parent, false);

        MyViewHolder holder = new MyViewHolder(bindView);
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
        FeedArticleData projectInfo = list.get(position);
        holder.bindView.itemProjectListTitleTv.setText(projectInfo.getTitle());
        holder.bindView.itemProjectListContentTv.setText(projectInfo.getDesc());
        holder.bindView.itemProjectListTimeTv.setText(projectInfo.getNiceDate());
        holder.bindView.itemProjectListAuthorTv.setText(projectInfo.getAuthor());
        Glide.with(context)
                .load(projectInfo.getEnvelopePic()) // 图片地址
                .apply(options) // 参数
                .into(holder.bindView.itemProjectListIv); // 需要显示的ImageView控件
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public ItemProjectListBinding bindView;

        public MyViewHolder(ItemProjectListBinding bindView) {
            super(bindView.getRoot());
            this.bindView = bindView;

        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}