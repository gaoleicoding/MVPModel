package com.gaolei.mvpmodel.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.gaolei.mvpmodel.R;
import com.gaolei.mvpmodel.base.mmodel.ProjectListData;
import com.gaolei.mvpmodel.base.thirdframe.glide.ImageLoader;

import java.util.List;


public class ProductDetailImgAdapter extends RecyclerView.Adapter<ProductDetailImgAdapter.MyViewHolder> {

    public Context context;
    int selectPosition = 0;
    OnItemClickListener listener;
    List<ProjectListData.ProjectData> list;
    String  TAG = "ProductDetailActivity";
    public ProductDetailImgAdapter(Context context,  List<ProjectListData.ProjectData> list) {
        this.context = context;
        this.list = list;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail_img, null);
        MyViewHolder holder = new MyViewHolder(view);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                int position = (int) view.getTag();
//                if (listener != null) {
//                    listener.onItemClick(view, position);
//                }
//            }
//        });
        return holder;
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemView.setTag(position);
        ProjectListData.ProjectData projectInfo=list.get(position);
        Log.d(TAG,"getEnvelopePic---------------"+projectInfo.getEnvelopePic());
//        setImageWidthHeight(holder.item_project_list_iv);
//        holder.item_tv_detail.setText(url);
        ImageLoader.getInstance().load(context, projectInfo.getEnvelopePic(), holder.item_iv_detail);
        //        Glide.with(context).load(url)
//                .listener(mRequestListener)
//                .into(holder.item_iv_detail);
//        Glide.with(context).load(url)
////                .error(R.drawable.ic_launcher)
//                .placeholder(R.drawable.ic_launcher)
//                .into(new SimpleTarget<GlideDrawable>() {
//                    @Override
//                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                        imageView.setImageDrawable(resource);
//                    }
//                });


    }
    RequestListener mRequestListener = new RequestListener() {
        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
            Log.d(TAG, "onException: " + e.toString()+"  model:"+model+" isFirstResource: "+isFirstResource);
//            imageView.setImageResource(R.mipmap.ic_launcher);
            return false;
        }

        @Override
        public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
            Log.e(TAG,  "model:"+model+" isFirstResource: "+isFirstResource);
            return false;
        }
    };

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        //        TextView item_project_list_title_tv, item_project_list_content_tv, item_project_list_time_tv, item_project_list_author_tv;
        ImageView item_iv_detail;
        TextView item_tv_detail;

        public MyViewHolder(View view) {
            super(view);
            item_iv_detail = view.findViewById(R.id.item_iv_detail);
//            item_tv_detail = view.findViewById(R.id.item_tv_detail);


        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View v, int position);
    }

}