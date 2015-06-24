package com.cn.businese;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cn.hongyi.R;

@SuppressLint("NewApi")
public class OrderFrament extends Fragment {
    
    TextView invalidTv, effectiveTv;
    
    int mNum;
    
    @SuppressLint("NewApi")
    static OrderFrament newInstance(int num) {
        OrderFrament orderfragment = new OrderFrament();
        Bundle args = new Bundle();
        args.putInt("num", num);
        orderfragment.setArguments(args);
        return orderfragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt("num") : 1;
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mNum == 1) {
            invalidTv.setVisibility(View.GONE);
            effectiveTv.setVisibility(View.VISIBLE);
        }
        else {
            invalidTv.setVisibility(View.VISIBLE);
            effectiveTv.setVisibility(View.GONE);
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.fragment_order_content, container, false);
        invalidTv = (TextView) layoutView.findViewById(R.id.invalid_tv);
        effectiveTv = (TextView) layoutView.findViewById(R.id.effective_tv);
        return layoutView;
    }
    
}
