package com.cn.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.widget.EditText;

/**
 * 对话框 项目名称：Hongyi 类名称：DialogUtils 类描述： 创建人：hongyi 创建时间：2015年1月12日 下午3:38:59
 * 修改人：hongyi 修改时间：2015年1月12日 下午3:38:59 修改备注：
 * 
 * @version
 */
public class DialogUtils {
    
    public static Dialog showDialog(Context mContext, String msg) {
        android.app.ProgressDialog dialog = new ProgressDialog(mContext);
        dialog.setTitle("提示信息");
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
                                                      .setTitle("喜好调查")
                                                      .setMessage("你喜欢李连杰的电影吗？")
                                                      .setPositiveButton("很喜欢", positiveListener)
                                                      .setNegativeButton("不喜欢", negativeListener)
                                                      .setNeutralButton("一般", neutraListener)
                                                      .setCancelable(true)
                                                      .create();
        dlg.show();
        return dlg;
    }
    
    public static Dialog showAlertDialog(final Context mContext,
                                         DialogInterface.OnClickListener mListener,
                                         DialogInterface.OnClickListener mDismissListener,
                                         EditText mEdit) {
        Dialog dialog = new AlertDialog.Builder(mContext).setTitle("请输入")
                                                         .setIcon(android.R.drawable.ic_dialog_info)
                                                         .setView(mEdit)
                                                         .setPositiveButton("确定", mListener)
                                                         .setNegativeButton("取消", mDismissListener)
                                                         .setCancelable(true)
                                                         .show();
        return dialog;
    }
    
    public static Dialog showMultyChoiceDialog(final Context mContext,
                                               OnMultiChoiceClickListener listener,
                                               DialogInterface.OnClickListener mlistener) {
        Dialog dialog = new AlertDialog.Builder(mContext).setTitle("复选框")
                                                         .setMultiChoiceItems(new String[] { "Item1", "Item2" },
                                                                              null,
                                                                              listener)
                                                         .setPositiveButton("确定", mlistener)
                                                         .setNegativeButton("取消", null)
                                                         .show();
        return dialog;
    }
    
    public static Dialog showSingleChoiceDialog(final Context mContext,
                                                DialogInterface.OnClickListener onClickListener,
                                                String[] str,
                                                DialogInterface.OnClickListener onConfirmClickListener) {
        Dialog dialog = new AlertDialog.Builder(mContext).setTitle("單選框")
                                                         .setSingleChoiceItems(str, 0, onClickListener)
                                                         .setPositiveButton("确定", onConfirmClickListener)
                                                         // .setNegativeButton("取消",
                                                         // onConfirmClickListener)
                                                         .show();
        return dialog;
    }
    
    public static Dialog showSingleChoiceDialog1(final Context mContext,
                                                 DialogInterface.OnClickListener onClickListener,
                                                 String[] str,
                                                 DialogInterface.OnClickListener onConfirmClickListener) {
        Dialog mDialog = new AlertDialog.Builder(mContext).setTitle("列表选项")
                                                          .setIcon(android.R.drawable.ic_dialog_alert)
                                                          .setSingleChoiceItems(str, 0, onClickListener)
                                                          .setPositiveButton("确定", onConfirmClickListener)
                                                          .setNegativeButton("取消", null)
                                                          .create();
        mDialog.show();
        return mDialog;
    }
    
    public static Dialog showSinDialog(final Context mContext, DialogInterface.OnClickListener onClickListener) {
        Dialog mDialog = new AlertDialog.Builder(mContext).setTitle("请选择")
                                                          .setIcon(android.R.drawable.ic_dialog_info)
                                                          .setSingleChoiceItems(new String[] { "选项1",
                                                                                              "选项2",
                                                                                              "选项3",
                                                                                              "选项4" },
                                                                                0,
                                                                               null)
                                                          .setNegativeButton("取消", onClickListener)
                                                          .show();
        
        return mDialog;
    }
    
}
