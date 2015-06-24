package com.cn.util;

import android.app.Dialog;

public interface HandlerCallBack {

    /**
     * ��������ص��ӿ�
     * 
     * @author genie
     */
        /**
         * ���ؽ�����ͼ ��ѡ
         * 
         * @param view
         */
        void onLoading(Dialog view);
        
        /**
         * �ɹ��ŵ��ø÷��� �������ݴ���
         * 
         * @param result
         *            ����ֵ
         */
        void onSuccess(String result);
        
        /**
         * �ͻ�������ʧ��
         * 
         * @param error
         *            ������Ϣ
         */
        void onError(String errMsg);
    }
