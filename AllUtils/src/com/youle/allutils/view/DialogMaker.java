package com.youle.allutils.view;

import android.R.integer;
import android.app.Dialog;

public class DialogMaker {
     public interface DialogCallBack{
    	public void onButtonClicked(Dialog dialog,int position,Object tag);
    	public void onCancelDialog(Dialog dialog,Object tag) ;
     }
}
