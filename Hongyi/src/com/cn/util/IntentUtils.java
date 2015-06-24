package com.cn.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;

import com.cn.businese.HomeActivity;
import com.cn.hongyi.R;

public class IntentUtils {
    
    /**
     * @description 添加快捷方式
     * @date 2015年4月9日
     * @param
     * @return void
     * @Exception
     */
    public static void addShortcutToDesktop(Context mContext) {
        
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        // 不允许重建
        shortcut.putExtra("duplicate", false);
        // 设置名字
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, mContext.getString(R.string.app_name));// 桌面快捷方式名称
        // 设置图标
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                          Intent.ShortcutIconResource.fromContext(mContext, R.drawable.ic_launcher));
        // 设置意图和快捷方式关联程序
        Intent intent = new Intent(mContext, HomeActivity.class);
        // 桌面图标和应用绑定，卸载应用后系统会同时自动删除图标
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
        // 发送广播
        mContext.sendBroadcast(shortcut);
    }
    
    /**
     * @description 快捷方式是否存在
     * @date 2015年4月9日
     * @param
     * @return boolean
     * @Exception
     */
    public static boolean isShortcutInstalled(Context mContext) {
        boolean isInstallShortcut = false;
        final ContentResolver cr = mContext.getContentResolver();
        // 2.2系统是”com.android.launcher2.settings”,网上见其他的为"com.android.launcher.settings"
        String AUTHORITY = null;
        /*
         * 根据版本号设置Uri的AUTHORITY
         */
        if (getSystemVersion() >= 8) {
            AUTHORITY = "com.android.launcher2.settings";
        }
        else {
            AUTHORITY = "com.android.launcher.settings";
        }
        
        Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/favorites?notify=true");
        Cursor c = cr.query(CONTENT_URI,
                            new String[] { "title", "iconResource" },
                            "title=?",
                            new String[] { mContext.getString(R.string.app_name) },
                            null);// 这里得保证app_name与创建
        // 快捷方式名的一致，否则会出现提示“快捷方式已经创建”
        if (c != null && c.getCount() > 0) {
            isInstallShortcut = true;
        }
        return isInstallShortcut;
    }
    
    /***
     * @description 返回SDK版本号
     * @date 2015年4月9日
     * @param
     * @return int
     * @Exception
     */
    @SuppressWarnings("deprecation")
    public static int getSystemVersion() {
        int sdkVersion = Integer.valueOf(android.os.Build.VERSION.SDK);
        return sdkVersion;
    }
    
}
