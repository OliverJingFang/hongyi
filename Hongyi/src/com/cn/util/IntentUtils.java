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
     * @description ��ӿ�ݷ�ʽ
     * @date 2015��4��9��
     * @param
     * @return void
     * @Exception
     */
    public static void addShortcutToDesktop(Context mContext) {
        
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        // �������ؽ�
        shortcut.putExtra("duplicate", false);
        // ��������
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, mContext.getString(R.string.app_name));// �����ݷ�ʽ����
        // ����ͼ��
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                          Intent.ShortcutIconResource.fromContext(mContext, R.drawable.ic_launcher));
        // ������ͼ�Ϳ�ݷ�ʽ��������
        Intent intent = new Intent(mContext, HomeActivity.class);
        // ����ͼ���Ӧ�ð󶨣�ж��Ӧ�ú�ϵͳ��ͬʱ�Զ�ɾ��ͼ��
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
        // ���͹㲥
        mContext.sendBroadcast(shortcut);
    }
    
    /**
     * @description ��ݷ�ʽ�Ƿ����
     * @date 2015��4��9��
     * @param
     * @return boolean
     * @Exception
     */
    public static boolean isShortcutInstalled(Context mContext) {
        boolean isInstallShortcut = false;
        final ContentResolver cr = mContext.getContentResolver();
        // 2.2ϵͳ�ǡ�com.android.launcher2.settings��,���ϼ�������Ϊ"com.android.launcher.settings"
        String AUTHORITY = null;
        /*
         * ���ݰ汾������Uri��AUTHORITY
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
                            null);// ����ñ�֤app_name�봴��
        // ��ݷ�ʽ����һ�£�����������ʾ����ݷ�ʽ�Ѿ�������
        if (c != null && c.getCount() > 0) {
            isInstallShortcut = true;
        }
        return isInstallShortcut;
    }
    
    /***
     * @description ����SDK�汾��
     * @date 2015��4��9��
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
