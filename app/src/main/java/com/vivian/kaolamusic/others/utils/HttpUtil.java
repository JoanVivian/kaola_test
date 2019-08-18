package com.vivian.kaolamusic.others.utils;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Download data by the network
 */
public class HttpUtil {

    /**
     * get request
     *
     * @param urlStr
     * @return
     */
    public static String doGet(String urlStr) {

        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlStr);
            //open connect
            connection = (HttpURLConnection) url.openConnection();
            //set method
            connection.setRequestMethod("GET");
            //set connect time out
            connection.setConnectTimeout(5000);
            //set read connect
            connection.setReadTimeout(5000);
            //connect
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
                String reader = null;
                StringBuilder stringBuilder = new StringBuilder();

                while ((reader = bufferedReader.readLine()) != null) {
                    stringBuilder.append(reader);
                }
                String result = stringBuilder.toString();
                return result;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != inputStream) {
                    inputStream.close();
                    inputStreamReader.close();
                    bufferedReader.close();
                    connection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Bitmap getBitmap(String urlPath) {
        String name = "" + urlPath.hashCode();
        File image = new File(FileUtil.DIR_IMAGE, name);
        if (image.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath());
            return bitmap;
        }
        InputStream is = null;
        FileOutputStream fos = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlPath);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                is = connection.getInputStream();
                fos = new FileOutputStream(image);
                //由inputStream获取Bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                //保存图片
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                return bitmap;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                    connection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
