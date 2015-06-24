package com.cn.businese;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.cn.hongyi.R;
import com.cn.util.DialogUtils;
import com.cn.util.FileUtils;
import com.cn.util.HandlerCallBack;
import com.cn.util.IntentUtils;
import com.cn.util.JSONUtils;
import com.cn.util.PackageUtils;
import com.cn.util.RoundedTransformationBuilder;
import com.cn.util.message.ToastUtil;
import com.cn.util.net.HttpRequest;
import com.cn.util.pull.PullingService;
import com.cn.util.pull.PullingUtils;
import com.cn.view.SingleSelectedDialog;
import com.cn.view.SingleSelectedDialog.onSingleSelectedListenter;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * 项目名称：Hongyi 类名称：HomeActivity 类描述： 创建人：hongyi 创建时间：2015年2月9日 上午11:21:40
 * 修改人：hongyi 修改时间：2015年2月9日 上午11:21:40 修改备注：
 * 
 * @version
 */
public class HomeActivity extends BaseActivity implements onSingleSelectedListenter {
    
    private Spinner mSpinner;
    
    private Button mButton, mLoginBtn, mShowDialogView;
    
    private ImageView mIvPic;
    
    private ArrayAdapter<String> mAdapter;
    
    private final String mAction = "json";
    
    private Context mContext;
    
    public static int mSelectViewHeight;
    
    public static Demo mInstance;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.i("FJ", "onCreate");
        setTitleName("索引页面");
        mContext = this;
        mSpinner = (Spinner) findViewById(R.id.spinner);
        mButton = (Button) findViewById(R.id.button);
        mLoginBtn = (Button) findViewById(R.id.btn_login);
        mShowDialogView = (Button) findViewById(R.id.btn_show_dialog);
        mIvPic = (ImageView) findViewById(R.id.pic_iv);
        
        SharedPreferences setting = getSharedPreferences(PackageUtils.getPackageName(), 0);
        Boolean user_first = setting.getBoolean("FIRST", true);
        if (user_first) {// 第一次
            setting.edit().putBoolean("FIRST", false).commit();
            Toast.makeText(this, "第一次", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "不是第一次", Toast.LENGTH_LONG).show();
        }
        
        if (IntentUtils.isShortcutInstalled(this)) {
            ToastUtil.showToast(this, "快捷方式已存在");
        }
        else {
            IntentUtils.addShortcutToDesktop(this);
            ToastUtil.showToast(this, "快捷方式已添加");
        }
        
        if (mInstance == null) {
            mInstance = new Demo();
        }
        
        Transformation mTransformation = new RoundedTransformationBuilder().borderColor(Color.WHITE)
                                                                           .borderWidthDp(0)
                                                                           .cornerRadiusDp(5)
                                                                           .oval(false)
                                                                           .build();
        
        Picasso.with(mContext)
               .load("http://b.hiphotos.baidu.com/baike/w%3D268/sign=f7aaa7709252982205333ec5efcb7b3b/e850352ac65c1038e2ca5575b0119313b17e89d5.jpg")
               .placeholder(R.drawable.icon_hotel_default)
               .error(R.drawable.icon_hotel_default)
               .fit()
               .transform(mTransformation)
               .into(mIvPic);
        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject mJsonObject = new JSONObject();
                mJsonObject.put("userName", "hongyi");
                HttpRequest.request(mContext, mJsonObject.toString(), mAction, new HandlerCallBack() {
                    
                    @Override
                    public void onSuccess(String result) {
                        HttpRequest.closeLoadingDialog();
                        List<String> cities = JSONUtils.parseCities(result);
                        mAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, cities);
                        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner.setAdapter(mAdapter);
                    }
                    
                    @Override
                    public void onLoading(Dialog view) {
                        HttpRequest.showDialog("正在加载中...");
                    }
                    
                    @Override
                    public void onError(String errMsg) {
                        HttpRequest.closeLoadingDialog();
                        
                    }
                });
            }
        });
        
        mLoginBtn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                /*
                 * JSONObject mJsonObject = new JSONObject();
                 * mJsonObject.put("userName", "hongyi"); mJsonObject.put("pwd",
                 * "fj123654"); HttpRequest.request(mContext,
                 * mJsonObject.toString(), "login", new HandlerCallBack() {
                 * @Override public void onSuccess(String result) {
                 * HttpRequest.closeLoadingDialog();
                 * ToastUtil.showToast(mContext, result); }
                 * @Override public void onLoading(Dialog view) {
                 * HttpRequest.showDialog("登录中。。。"); }
                 * @Override public void onError(String errMsg) {
                 * HttpRequest.closeLoadingDialog(); } });
                 */
                startActivity(new Intent(HomeActivity.this, TestActivity.class));
                
            }
        });
        
        findViewById(R.id.btn_pull_up_refresh).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, RreshActivity.class));
            }
        });
        
        findViewById(R.id.btn_open_bd_map).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, BaiduMapActivity.class));
            }
        });
        
        mShowDialogView.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                final EditText mEdit = new EditText(mContext);
                DialogUtils.showAlertDialog(mContext, new DialogInterface.OnClickListener() {
                    
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtil.showToast(HomeActivity.this, "您輸入的是" + mEdit.getText());
                        Log.i("HY", "点击确认。。。。" + mEdit.getText());
                    }
                }, new DialogInterface.OnClickListener() {
                    
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }, mEdit).show();
            }
        });
        
        findViewById(R.id.btn_show_dialog2).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                DialogUtils.showMultyChoiceDialog(mContext, new OnMultiChoiceClickListener() {
                    
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        ToastUtil.showToast(mContext, "现在你点击的" + which);
                    }
                }, new DialogInterface.OnClickListener() {
                    
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtil.showToast(mContext, "现在你点击的1" + which);
                    }
                });
            }
        });
        
        findViewById(R.id.btn_show_dialog3).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                DialogUtils.showSinDialog(mContext, new DialogInterface.OnClickListener() {
                    
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtil.showToast(mContext, "您选中了第" + which + "个");
                    }
                });
            }
        });
        
        findViewById(R.id.btn_show_dialog4).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                ArrayList<String> mList = new ArrayList<String>();
                mList.add("李明");
                mList.add("王晓");
                mList.add("韩梅梅");
                mList.add("张三");
                SingleSelectedDialog mDialog = new SingleSelectedDialog(mContext, "请选择", mList, R.style.ProgressDialog);
                mDialog.show();
                mDialog.setSingleSelectedListener(HomeActivity.this);
            }
        });
        
        findViewById(R.id.btn_open_braodcast).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ShowBroadCastActivity.class));
            }
        });
        
        findViewById(R.id.btn_show_pwd).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ShowPwdAndImageActivity.class));
                finish();
            }
        });
        
        findViewById(R.id.btn_show_test).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                String result = FileUtils.readSystemFile(mContext, "fj");
                Log.i("FJ", "结果： " + result);
            }
        });
        
        findViewById(R.id.btn_write_file).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                FileUtils.writeStrToSDCard("mymymmmmmm", "fffjjj.txt");
            }
        });
        
        System.out.println("Start polling service...");
        PullingUtils.startPollingService(this, 2, PullingService.class, PullingService.ACTION);
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("FJ", "onStart");
    }
    
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("FJ", "onRestart");
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("FJ", "onResume");
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("FJ", "onPause");
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("FJ", "onStop");
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("FYL", "onDestroy");
       // PullingUtils.stopPollingService(this, PullingService.class, PullingService.ACTION);
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("FJ", "onRestoreInstanceState ");
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("FJ", "onSaveInstanceState ");
    }
    
    @Override
    public void onItemSelected(String item, int position) {
        ToastUtil.showToast(mContext, "您选择的是" + item);
    }
    
    class Demo {
        void doSomething() {
            Log.i("FJ", "dosth.");
        }
    }
    
}
