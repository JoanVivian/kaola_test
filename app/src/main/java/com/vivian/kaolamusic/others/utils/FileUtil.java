package com.vivian.kaolamusic.others.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import java.io.File;

/**
 * Created by vivian on 2019/8/18 15
 */
public class FileUtil {
    public static final String APP_DIR="KaoLaMusic";
    public static final File DIR_IMAGE= getDir("image");

    /**
     * 获取根目录
     * @return
     */
    public static File getSdCardDir(){
        //获取sd卡的状态
        String state= Environment.getExternalStorageState();
        //如果sd卡挂起
        if (Environment.MEDIA_MOUNTED==state){
            //获取sd卡的根目录
            File directory = Environment.getExternalStorageDirectory();
            return directory;
        }
        return null;
    }

    /**
     * 获取app的目录
     * @return
     */
    public static File getAppDir(){
        File file=new File(getSdCardDir(),APP_DIR);
        if (file.exists()){
            file.mkdir();
        }
        return file;
    }

    /**
     * 获取app目录下的子项目
     * @param dirName
     * @return
     */
    public static File getDir(String dirName){
        File file=new File(getAppDir(),dirName);
        if (file.exists()){
            file.mkdir();
        }
        return file;
    }

    /**
     * 获取sharedPreference对应key的value
     * @param context
     * @param fileName
     * @param flag
     * @return
     */
    public static String getCatchFromReference(Context context,String fileName,String flag){
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(flag,"");
        return value;

    }

    /**
     * 获取sharedPreference的时间
     * @param context
     * @param fileName
     * @param flag
     * @return
     */
    public static long getCatchTimeFromPreference(Context context,String fileName,String flag){
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        long value = sharedPreferences.getLong(flag, 0);
        return value;
    }

    /**
     * 保存Json字符串到SharedPreference
     * @param context
     * @param fileName
     * @param flagName
     * @param json
     */
    public static void saveJsonToPreference(Context context,String fileName,String flagName, String json){
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(flagName,json);
        edit.commit();
    }

    /**
     * 保存请求Json数据的时间
     * @param context
     * @param fileName
     * @param flageName
     * @param time
     */
    public static void saveTimeToPreference(Context context,String fileName,String flageName,long time){
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong(flageName,time);
        edit.commit();
    }
}
