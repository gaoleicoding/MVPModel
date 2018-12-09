package com.gaolei.mvpmodel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.base.mmodel.ArticleListData.FeedArticleData;
import com.gaolei.mvpmodel.base.thirdframe.glide.ImageLoader;

import java.util.List;


public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.MyViewHolder> {

    public Context context;
    int selectPosition = 0;
    OnItemClickListener listener;
    List<FeedArticleData> list;

    public ArticleListAdapter(Context context, List<FeedArticleData> list) {
        this.context = context;
        this.list = list;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_article_list, null);
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
        FeedArticleData projectInfo = list.get(position);

        holder.tv_item_title.setText(projectInfo.getTitle());
        holder.tv_item_time.setText(projectInfo.getNiceDate());
        holder.tv_item_author.setText(context.getString(R.string.author) + projectInfo.getAuthor());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item_title, tv_item_author, tv_item_time;

        public MyViewHolder(View view) {
            super(view);
            tv_item_title = view.findViewById(R.id.tv_item_title);
            tv_item_author = view.findViewById(R.id.tv_item_author);
            tv_item_time = view.findViewById(R.id.tv_item_time);
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}