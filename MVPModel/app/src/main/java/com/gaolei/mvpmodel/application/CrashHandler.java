package com.gaolei.mvpmodel.application;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    /** 系统默认的异常处理类 */
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    Context mcontext;
    private static CrashHandler INSTANCE = new CrashHandler();
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
                Thread.sleep(1000);
            } catch (Exception e) {
                // TODO: handle exception
            }
            // 退出程序
            // android.os.Process.killProcess(android.os.Process.myPid());
            // System.exit(1);
            // mcontext.startActivity(new Intent(mcontext, LogoActivity.class));

//          Intent intent = new Intent(mcontext.getApplicationContext(),
//                  LogoActivity.class);
//          intent.putExtra("Error", true);
//          intent.putExtra("FilePath", file);
//
//          PendingIntent pendingIntent = PendingIntent.getActivity(
//                  mcontext.getApplicationContext(), 0, intent,
//                  Intent.FLAG_ACTIVITY_NEW_TASK);
//          AlarmManager mgr = (AlarmManager) mcontext
//                  .getSystemService(Context.ALARM_SERVICE);
//          mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 400,
//                  pendingIntent); // 1秒钟后重启应用
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
                Toast.makeText(mcontext, "程序出错了，即将退出！我们会尽快修复!",
                        Toast.LENGTH_LONG).show();
                Looper.loop();
            };
        }.start();
        saveCrashInfoIntoSd(ex);
        return true;// 测试阶段全部抛出
        // return false;
    }

    /**
     * 保存错误信息到SD卡文件中
     *
     * @param ex
     * @return 返回文件名称,便于将文件传送到服务器
     */
    private void saveCrashInfoIntoSd(Throwable ex) {
        if(isSdCardExist()) {
            String errPath = Environment.getExternalStorageDirectory().getPath()+"/"+mcontext.getPackageName()+"/crash";

            File dir = new File(errPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = formatter.format(new Date());
            String fileName = "crash-" + time + ".txt";

            try {

                Writer writer = new StringWriter();
                PrintWriter printWriter = new PrintWriter(writer);
                ex.printStackTrace(printWriter);
                Throwable cause = ex.getCause();
                while (cause != null) {
                    cause.printStackTrace(printWriter);
                    cause = cause.getCause();
                }
                printWriter.close();
                final String result = writer.toString();

                File file = new File(errPath + "//" + fileName);
                if(!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(result.toString().getBytes());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断SDCard是否存在 [当没有外挂SD卡时，内置ROM也被识别为存在sd卡]
     *
     * @return
     */
    public static boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }
}