package com.gaolei.mvpmodel.base.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

import com.gaolei.runtimepermissionapplication.MainActivity;
import com.gaolei.runtimepermissionapplication.application.CustomApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    /**
     * 系统默认的异常处理类
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    Context mcontext;
    private static CrashHandler INSTANCE = new CrashHandler();
    String errorSavePath;
    //用来存储设备信息和异常信息
    private Map<String, String> infos = new LinkedHashMap();

    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        mcontext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();// 获取默认的异常处理类
        Thread.setDefaultUncaughtExceptionHandler(this);// 设置当前处理类为默认的异常处理类
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, ex);// 如果未处理异常，那么系统默认的异常处理类处理
        } else {
            try {
                Thread.sleep(1500);
            } catch (Exception e) {
                // TODO: handle exception
            }

            //崩溃后，重启应用
            Intent intent = new Intent(mcontext.getApplicationContext(),
                    MainActivity.class);
            intent.putExtra("error_reboot", true);

            PendingIntent pendingIntent = PendingIntent.getActivity(
                    mcontext.getApplicationContext(), 0, intent,
                    PendingIntent.FLAG_ONE_SHOT);
            AlarmManager mgr = (AlarmManager) mcontext
                    .getSystemService(Context.ALARM_SERVICE);
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1500,
                    pendingIntent); // 1秒钟后重启应用
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(10);
        }
    }

    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast toast = Toast.makeText(mcontext, "程序出错了，我们会尽快修复，稍后将重启应用！",
                        Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                Looper.loop();
            }
        }.start();

        collectDeviceInfo();
        saveCrashInfoIntoSd(ex);
        return true;// 测试阶段全部抛出
        // return false;
    }

    // 收集设备、软件参数信息
    private void collectDeviceInfo() {
        try {
            PackageManager pm = CustomApplication.context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(CustomApplication.context.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("systemVersion", SystemUtil.getSystemVersion());
                infos.put("deviceModel", SystemUtil.getSystemModel());
                infos.put("deviceBrand", SystemUtil.getDeviceBrand());
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
        }

    }

    // 保存错误信息到SD卡文件中
    private void saveCrashInfoIntoSd(Throwable ex) {
        //创建文件夹
        errorSavePath = Environment.getExternalStorageDirectory().getPath() + "/" + mcontext.getPackageName() + "/crash";
        File dir = new File(errorSavePath);
        if (!dir.exists()) dir.mkdirs();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = formatter.format(new Date());
        StringBuffer sb = new StringBuffer();
        sb.append("\n" + time + "\n");
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        sb.append(getCrashInfo(ex));

        try {
            //创建文件
            String fileName = "crash-" + time + ".txt";
            File file = new File(errorSavePath + "//" + fileName);
            if (!file.exists()) file.createNewFile();

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(sb.toString().getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到程序崩溃的详细信息
     */
    public String getCrashInfo(Throwable ex) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        ex.setStackTrace(ex.getStackTrace());
        ex.printStackTrace(printWriter);
        printWriter.close();
        return result.toString();
    }
}