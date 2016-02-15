package com.youle.allutils.activity;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.youle.allutils.util.ActivityStack;
import com.youle.allutils.view.DialogMaker;
import com.youle.allutils.view.DialogMaker.DialogCallBack;

import android.R.bool;
import android.R.integer;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity implements DialogCallBack{
          
	private boolean isCreate=false;
	private AlertDialog dialog;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		ActivityStack.getInstance().addActivity(this);
		setContentView(getLayoutID());
		ViewUtils.inject(this);
		isCreate=true;
	}
	@Override
	protected void onResume() {
		super.onResume();
		if (isCreate) {
			isCreate=false;
			initParams();
		}
	}
	
	//参数设置
	protected  abstract void initParams();
	//初始化布局
	protected abstract int getLayoutID();
	
	
	//弹出对话框
	public Dialog showAlertDialog(String title,String msg,String[] btns,boolean isCanCancelable,final boolean isDismissAfterClickBtn,Object tag){
		if (null==dialog|| !dialog.isShowing()) {
			//dialog=DialogMaker.showCommonAlertDialog(this,title,msg,btns,this,isCanCancelable,isDismissAfterClickBtn,tag);
		}
		return dialog;
	}
	//等待对话框
	public Dialog showWaitDialog(String msg,boolean isCanCancelabel,Object tag) {
		if (null==dialog||!dialog.isShowing()) {
			//dialog=DialogMaker.showCommonAlertDialog(this,msg,this,isCanCancelabel,tag);
			
		}
		return dialog;
		
	}
	//关闭对话框
	public void dismissDialog(){
		if (null!=dialog&&dialog.isShowing()) {
			dialog.dismiss();
		}
	}
	@Override
	protected void onDestroy() {
	   dismissDialog();
	   ActivityStack.getInstance().removeActivity(this);
		super.onDestroy();
	}
	@Override
	public void onButtonClicked(Dialog dialog, int position, Object tag) {
	}
	@Override
	public void onCancelDialog(Dialog dialog, Object tag) {
	}
	
}
