package com.cn.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.http.util.EncodingUtils;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;

public class FileUtils {
    
    /**
     * @description ��ȡ�ļ��Ľ�β
     * @date 2015��3��13��
     * @param
     * @return String
     * @Exception
     */
    public static String getFileNameEnds(File file) {
        String fileName = file.getName();
        int index = fileName.indexOf('.');
        if (index != -1) {
            int length = fileName.length();
            String str = fileName.substring(index, length);
            return str;
        }
        return "";
    }
    
    public static String getLastFileName(String filePah) {
        return filePah.substring(filePah.lastIndexOf("/") + 1);
    }
    
    /**
     * @description д���ļ����ڲ��ļ���
     * @date 2015��4��20��
     * @param
     * @return void
     * @Exception
     */
    public static void writeStrToSystem(Context context, String fileName, String content) {
        FileOutputStream mOutputStream = null;
        try {
            mOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            mOutputStream.write(content.getBytes());
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                mOutputStream.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    /**
     * @description ��ȡϵͳ�ļ�������
     * @date 2015��4��20��
     * @param
     * @return String
     * @Exception
     */
    public static String readSystemFile(Context context, String fileName) {
        FileInputStream fin = null;
        String result = "";
        try {
            fin = context.openFileInput(fileName);
            // ��ó���
            int length;
            length = fin.available();
            // �����ֽ�����
            byte[] buffer = new byte[length];
            // ��ȡ����
            fin.read(buffer);
            result = EncodingUtils.getString(buffer, "utf-8");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    /**
     * @description ɾ������ϵͳ�ļ�
     * @date 2015��4��20��
     * @param
     * @return boolean
     * @Exception
     */
    @SuppressLint("SdCardPath")
    public static boolean deleteSystemFile(Context context, String fileName) {
        String absoluteFilePath = "/data/data/" + PackageUtils.getPackageName() + "/files/" + fileName;
        File mFile = new File(absoluteFilePath);
        return mFile.delete();
    }
    
    /**
     * �����ļ���
     * 
     * @author Administrator
     */
    public static void writeStrToSDCard(String content, String fileName) {
        FileOutputStream mOutputStream = null;
        try {
            File mFilePath = null;
            // �ж��Ƿ����ڴ濨���Ҿ���д��Ȩ��
            if ("mounted".equals(Environment.getExternalStorageState())) {
                mFilePath = new File(Environment.getExternalStorageDirectory(), "/hongyi");
            }
            else {
                mFilePath = new File("/hongyi");
            }
            if (!mFilePath.exists()) {
                mFilePath.mkdirs();
            }
            File file = new File(mFilePath.getAbsolutePath() + "/" + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            mOutputStream = new FileOutputStream(file);
            byte[] mContentBytes = content.getBytes();
            mOutputStream.write(mContentBytes);
            mOutputStream.flush();
            mOutputStream.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("resource")
    public static String readSDCardContent(String filePath) throws IOException {
        File mFile = new File(filePath);
        FileInputStream mIn = null;
        int fileSize = 0;
        if (!mFile.exists()) {
            return "";
        }
        else {
            mIn = new FileInputStream(mFile);
            fileSize = mIn.available();
            byte[] fileSizeByte = new byte[fileSize];
            mIn.read(fileSizeByte);
            return EncodingUtils.getString(fileSizeByte, "UTF-8");
        }
    }
    
}
