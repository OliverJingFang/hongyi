package com.cn.util.net;

import java.io.IOException;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;

import com.cn.modle.Request;
import com.cn.util.DialogUtils;
import com.cn.util.HandlerCallBack;
import com.cn.util.message.ToastUtil;

/**
 * ���������� ��Ŀ���ƣ�Hongyi �����ƣ�HttpRequest �������� �����ˣ�hongyi ����ʱ�䣺2015��1��12�� ����3:45:48
 * �޸��ˣ�hongyi �޸�ʱ�䣺2015��1��12�� ����3:45:48 �޸ı�ע��
 * 
 * @version
 */
public class HttpRequest {
    
    public static Context mContext;
    
    public static Dialog mDialog;
    
    public static Message msg = new Message();
    
    public static Handler errorHandler = new Handler(new Handler.Callback() {
        
        @Override
        public boolean handleMessage(Message msg) {
            Request mRequest = (Request) msg.obj;
            String mMsg = "";
            switch (msg.what) {
                case ErrorCode.JSON_ERROR:
                    mMsg = "JSON��������";
                    break;
                case ErrorCode.NETWORK_TIMEOUT:
                    mMsg = "�������ӳ�ʱ";
                    break;
                case ErrorCode.UNKNOW_ERROR:
                    mMsg = "λ�ô���";
                    break;
            }
            ToastUtil.showToast(mContext, mMsg);
            mRequest.callback.onError(mMsg);
            return false;
        }
    });
    
    public static final void request(Context context, String param, String action, final HandlerCallBack callback) {
        mContext = context;
        Request mRequest = new Request();
        mRequest.param = param;
        mRequest.action = action;
        mRequest.callback = callback;
        callback.onLoading(mDialog);
        requestAsyncTask task = new requestAsyncTask(mRequest);
        task.execute(mRequest);
    }
    
    public static void closeLoadingDialog() {
        cancel();
    }
    
    public static void showDialog(String msg) {
        if (mDialog == null) {
            mDialog = DialogUtils.showDialog(mContext, msg);
        }
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
        mDialog.setOnKeyListener(dialogKeyListener);
    }
    
    private static DialogInterface.OnKeyListener dialogKeyListener = new DialogInterface.OnKeyListener() {
        @Override
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                cancel();
            }
            return false;
        }
    };
    
    /**
     * �رնԻ���
     */
    public static void cancel() {
        if (mDialog != null) {
            if (mDialog.isShowing()) {
                mDialog.dismiss();
                mDialog = null;
            }
        }
    }
    
    public static class requestAsyncTask extends AsyncTask<Request, Void, String> {
        
        Request request;
        
        public requestAsyncTask(Request request) {
            this.request = request;
        }
        
        @Override
        protected void onPreExecute() {
            
        }
        
        @Override
        protected String doInBackground(Request... params) {
            String citiesString = "";
            Log.i("HttpRequest", params[0].param);
            Log.i("HttpRequest", params[0].action);
            // �����������˵�json����
            try {
                citiesString = HttpUtils.requestData(params[0]);
            }
            catch (org.json.JSONException e) {
                msg = errorHandler.obtainMessage(ErrorCode.JSON_ERROR, request);
                msg.sendToTarget();
                e.printStackTrace();
            }
            catch (IOException e) {
                msg = errorHandler.obtainMessage(ErrorCode.NETWORK_TIMEOUT, request);
                msg.sendToTarget();
                e.printStackTrace();
            }
            catch (Exception e) {
                msg = errorHandler.obtainMessage(ErrorCode.UNKNOW_ERROR, request);
                msg.sendToTarget();
                e.printStackTrace();
            }
            
            return citiesString;
        }
        
        @Override
        protected void onPostExecute(String result) {
            request.callback.onSuccess(result);
        }
    }
    
}
