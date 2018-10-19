/**
 * Copyright 2015
 * All right reserved.
 * Create on 2015年5月13日 下午3:14:25
 */
package com.storage.storagecardapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author Hi jackie
 */
public class StorageVideoDialog extends Dialog {
    // private Button btnConfirm;
    // private Button btnCancel;

    // public static final int PATH_VIDEO_SD = 1;
    // public static final int PATH_VIDEO_MEMORY = 0;
    private Context mContext;
    private LinearLayout layout_select_memory, layout_select_sdcard;
    private String strTitle;
    private TextView tvTitle, txt_select_path;
    private TextView txt_memory_space, txt_sdcard_space;
    private ImageView icon_select_memory, icon_select_sdcard;
    private int currentPath = ConstantUtils.PATH_VIDEO_MEMORY;
    private InfoCallback callback;
    private boolean hasSDCard;

    public StorageVideoDialog(Context context, int theme) {
        super(context, theme);
        // TODO Auto-generated constructor stub
        mContext = context;
    }

    /**
     * @return the hasSDCard
     */
    public boolean isHasSDCard() {
        return hasSDCard;
    }

    /**
     * @param hasSDCard the hasSDCard to set
     */
    public void setHasSDCard(boolean hasSDCard) {
        this.hasSDCard = hasSDCard;
    }

    public StorageVideoDialog(Context context, int theme, InfoCallback cback) {
        super(context, theme);
        // TODO Auto-generated constructor stub
        mContext = context;
        this.callback = cback;
    }

    public void setCheckedData(int path) {

        currentPath = path;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_save_video_position);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        tvTitle = findViewById(R.id.text_dialog_save_video_title);
        txt_select_path = findViewById(R.id.txt_select_path);
        icon_select_memory = findViewById(R.id.icon_select_memory);
        icon_select_sdcard = findViewById(R.id.icon_select_sdcard);
        txt_memory_space = findViewById(R.id.txt_memory_space);
        txt_sdcard_space = findViewById(R.id.txt_sdcard_space);

        layout_select_memory = findViewById(R.id.layout_select_memory);
        layout_select_sdcard = findViewById(R.id.layout_select_sdcard);
        if(MyStorageManager.paths!=null) {
            if (MyStorageManager.paths.size() == 1) layout_select_sdcard.setVisibility(View.GONE);
            if (MyStorageManager.paths.size() > 0)
                txt_memory_space.setText(SDUtils.getEmptySize(MyStorageManager.paths.get(0).split("Android")[0]));
            if (MyStorageManager.paths.size() > 1)
                txt_sdcard_space.setText(SDUtils.getEmptySize(MyStorageManager.paths.get(1).split("Android")[0]));
        }
        //默认选择
        initListener();
    }

    public void setOnInfoCallback(InfoCallback callback) {
        this.callback = callback;
    }

    public void initListener() {
        layout_select_memory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMemory();
                callback.onSelected(ConstantUtils.PATH_VIDEO_MEMORY);
            }
        });
        layout_select_sdcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectSdcard();
                callback.onSelected(ConstantUtils.PATH_VIDEO_SD);
            }
        });

    }

    @Override
    public void show() {
        super.show();
        if (!TextUtils.isEmpty(strTitle)) {
            tvTitle.setText(strTitle);
        }
        setData();
    }

    private void setData() {
        if (PreferenceUtils.getPrefInt(BaseApplication.mContext, ConstantUtils.KEY_SP_PATH, ConstantUtils.PATH_VIDEO_MEMORY) ==
                ConstantUtils.PATH_VIDEO_MEMORY) {
            selectMemory();

        } else {

            selectSdcard();
        }
    }

    public void setDialogTitle(String str) {
        strTitle = str;
    }

    public interface InfoCallback {
        public void onSelected(int position);
    }

    private void selectSdcard() {
        icon_select_memory.setImageResource(R.mipmap.radio_button_unselect);
        icon_select_sdcard.setImageResource(R.mipmap.radio_button_select);
        txt_select_path.setText(String.format(mContext.getString(R.string.current_download_path), "Android/data/com.xuetangx.mobile"));
    }

    private void selectMemory() {
        icon_select_memory.setImageResource(R.drawable.radio_button_select);
        icon_select_sdcard.setImageResource(R.drawable.radio_button_unselect);
        txt_select_path.setText(String.format(mContext.getString(R.string.current_download_path), ConstantUtils.P_VIDEO));

    }

}
