package com.cn.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.widget.EditText;

/**
 * �Ի��� ��Ŀ���ƣ�Hongyi �����ƣ�DialogUtils �������� �����ˣ�hongyi ����ʱ�䣺2015��1��12�� ����3:38:59
 * �޸��ˣ�hongyi �޸�ʱ�䣺2015��1��12�� ����3:38:59 �޸ı�ע��
 * 
 * @version
 */
public class DialogUtils {
    
    public static Dialog showDialog(Context mContext, String msg) {
        android.app.ProgressDialog dialog = new ProgressDialog(mContext);
        dialog.setTitle("��ʾ��Ϣ");
        dialog.setMessage(msg);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        return dialog;
    }
    
    public static Dialog showChoiceDialog(final Context mContext,
                                          DialogInterface.OnClickListener positiveListener,
                                          DialogInterface.OnClickListener neutraListener,
                                          DialogInterface.OnClickListener negativeListener) {
        Dialog dlg = new AlertDialog.Builder(mContext).setIcon(android.R.drawable.btn_star)
                                                      .setTitle("ϲ�õ���")
                                                      .setMessage("��ϲ�������ܵĵ�Ӱ��")
                                                      .setPositiveButton("��ϲ��", positiveListener)
                                                      .setNegativeButton("��ϲ��", negativeListener)
                                                      .setNeutralButton("һ��", neutraListener)
                                                      .setCancelable(true)
                                                      .create();
        dlg.show();
        return dlg;
    }
    
    public static Dialog showAlertDialog(final Context mContext,
                                         DialogInterface.OnClickListener mListener,
                                         DialogInterface.OnClickListener mDismissListener,
                                         EditText mEdit) {
        Dialog dialog = new AlertDialog.Builder(mContext).setTitle("������")
                                                         .setIcon(android.R.drawable.ic_dialog_info)
                                                         .setView(mEdit)
                                                         .setPositiveButton("ȷ��", mListener)
                                                         .setNegativeButton("ȡ��", mDismissListener)
                                                         .setCancelable(true)
                                                         .show();
        return dialog;
    }
    
    public static Dialog showMultyChoiceDialog(final Context mContext,
                                               OnMultiChoiceClickListener listener,
                                               DialogInterface.OnClickListener mlistener) {
        Dialog dialog = new AlertDialog.Builder(mContext).setTitle("��ѡ��")
                                                         .setMultiChoiceItems(new String[] { "Item1", "Item2" },
                                                                              null,
                                                                              listener)
                                                         .setPositiveButton("ȷ��", mlistener)
                                                         .setNegativeButton("ȡ��", null)
                                                         .show();
        return dialog;
    }
    
    public static Dialog showSingleChoiceDialog(final Context mContext,
                                                DialogInterface.OnClickListener onClickListener,
                                                String[] str,
                                                DialogInterface.OnClickListener onConfirmClickListener) {
        Dialog dialog = new AlertDialog.Builder(mContext).setTitle("���x��")
                                                         .setSingleChoiceItems(str, 0, onClickListener)
                                                         .setPositiveButton("ȷ��", onConfirmClickListener)
                                                         // .setNegativeButton("ȡ��",
                                                         // onConfirmClickListener)
                                                         .show();
        return dialog;
    }
    
    public static Dialog showSingleChoiceDialog1(final Context mContext,
                                                 DialogInterface.OnClickListener onClickListener,
                                                 String[] str,
                                                 DialogInterface.OnClickListener onConfirmClickListener) {
        Dialog mDialog = new AlertDialog.Builder(mContext).setTitle("�б�ѡ��")
                                                          .setIcon(android.R.drawable.ic_dialog_alert)
                                                          .setSingleChoiceItems(str, 0, onClickListener)
                                                          .setPositiveButton("ȷ��", onConfirmClickListener)
                                                          .setNegativeButton("ȡ��", null)
                                                          .create();
        mDialog.show();
        return mDialog;
    }
    
    public static Dialog showSinDialog(final Context mContext, DialogInterface.OnClickListener onClickListener) {
        Dialog mDialog = new AlertDialog.Builder(mContext).setTitle("��ѡ��")
                                                          .setIcon(android.R.drawable.ic_dialog_info)
                                                          .setSingleChoiceItems(new String[] { "ѡ��1",
                                                                                              "ѡ��2",
                                                                                              "ѡ��3",
                                                                                              "ѡ��4" },
                                                                                0,
                                                                               null)
                                                          .setNegativeButton("ȡ��", onClickListener)
                                                          .show();
        
        return mDialog;
    }
    
}
