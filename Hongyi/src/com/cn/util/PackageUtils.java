package com.cn.util;

import java.io.File;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.util.Log;
import com.cn.HyApp;

    /**
     * 
     *     
     * 项目名称：Hongyi    
     * 类名称：PackageUtils    
     * 类描述：    
     * 创建人：hongyi    
     * 创建时间：2015年4月9日 下午2:29:14    
     * 修改人：hongyi    
     * 修改时间：2015年4月9日 下午2:29:14    
     * 修改备注：    
     * @version     
     *
     */
    public final class PackageUtils {

        private static Context mContext = HyApp.getInstance();
        
        /**
         * 获取sdk版本号
         * @Description: TODO 
         * @return
         * @throws
         */
        public static int getSDKVersion() {
          int version = 0;
          try {
              version = Integer.valueOf(android.os.Build.VERSION.SDK);
          } catch (NumberFormatException e) {
              
          }
          return version;
        }

        public static int getVersionCode() {
            int versionCode = -1;
            try {
                versionCode = mContext.getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
                return versionCode;
            }
            catch (NameNotFoundException e) {
                e.printStackTrace();
                return -1;
            }
        }

        public static String getVersionName() {
            String versionName = "";
            try { 
                versionName = mContext.getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
                return versionName;
            }
            catch (NameNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }

        public static String getPackageName() {
            if (mContext != null) {
                return mContext.getPackageName();
            }
            return "com.na517";
        }

        /**
         * 安装APK
         * 
         * @param file
         * @param context
         */
        public static void installAPK(File file, Context context) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri data = Uri.fromFile(file);
            String type = "application/vnd.android.package-archive";
            intent.setDataAndType(data, type);
            context.startActivity(intent);
        }

        /**
         * 卸载APK
         * 
         * @param packageName
         * @param context
         */
        public static void uninstallAPK(String packageName, Context context) {
            Intent intent = new Intent(Intent.ACTION_DELETE);
            Uri data = Uri.parse("package:" + packageName);
            intent.setData(data);
            context.startActivity(intent);
        }

        /**
         * get currnet activity's name
         * 
         * @param context
         * @return
         */
        public static String getActivityName(Context context) {
            if (context == null) {
                return "";
            }
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            if (checkPermissions(context, "android.permission.GET_TASKS")) {
                ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
                String activityName = cn.getShortClassName();
                activityName = activityName.substring(activityName.lastIndexOf(".") + 1, activityName.length());
                return activityName;
            }
            else {
                Log.e("lost permission", "android.permission.GET_TASKS");
                return "";
            }

        }

        /**
         * checkPermissions
         * 
         * @param context
         * @param permission
         * @return true or false
         */
        public static boolean checkPermissions(Context context, String permission) {
            PackageManager localPackageManager = context.getPackageManager();
            return localPackageManager.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED;
        }
        /**
         * @description 获取APPLICATION metadata的数据
         * @date 2014-12-2 
         * @param 
         * @return String
         * @Exception
         */
         public static Object getApplicationMetaData(Context context , String key ) {
                Object sRst = null;
                ApplicationInfo appInfo = null;
                if( context == null || StringUtils.isEmpty(key) ) {
                    return null;
                }
                try {
                    appInfo = context.getPackageManager().getApplicationInfo(getPackageName(),PackageManager.GET_META_DATA);
                    sRst = appInfo.metaData.get(key);
                }
                catch (NameNotFoundException e) {
                    e.printStackTrace();
                }
                return sRst;
            }

    }

