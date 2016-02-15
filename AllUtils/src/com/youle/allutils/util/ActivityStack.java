package com.youle.allutils.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import android.R.bool;
import android.R.integer;
import android.app.Activity;

public class ActivityStack {
    private static ActivityStack mSingleInstance;
    private Stack<Activity> mActivityStack;
    
    private ActivityStack(){
    	mActivityStack=new Stack<Activity>();
    };
    
    public static ActivityStack getInstance() {
		if (null==mSingleInstance) {
			mSingleInstance=new ActivityStack();
		}
		return mSingleInstance;
	}
    public Stack<Activity> getStack() {
		return mActivityStack; 
	}
    //入栈
    public void addActivity(Activity activity){
    	mActivityStack.push(activity);
    }
    //出栈
    public void removeActivity(Activity activity){
    	mActivityStack.remove(activity);
    }
    //彻底退出
    public void finishAllActivity(){
    	Activity activity;
    	while (!mActivityStack.empty()) {
			activity =mActivityStack.pop();
			if (activity!=null) {
				activity.finish();
			}
			
		}
    }
    //finish指定的Activity
    public boolean finishActivity(Class<? extends Activity> actCls) {
    	Activity act=findActivityByClass(actCls);
    	if (null!=act&&!act.isFinishing()) {
			act.finish();
			return true;
		}
    	return false;
		
	}

	private Activity findActivityByClass(Class<? extends Activity> actCls) {
		Activity aActivity=null;
		Iterator<Activity> itr=mActivityStack.iterator();
		while (itr.hasNext()) {
			aActivity=itr.next();
			if (null!=aActivity&&aActivity.getClass().getName().equals(actCls.getName())&&!aActivity.isFinishing()) {
				break;
			}
			aActivity=null;
			
		}
		return aActivity;
		
	}
	//finish指定的activity之上的所有activity
	public boolean  finishToActivity(Class<? extends Activity> actCls,boolean isIncludeSelf) {
		List<Activity> buf=new ArrayList<Activity>();
		int size=mActivityStack.size();
		Activity activity=null;
		for (int i = size-1; i >=0; i--) {
			activity=mActivityStack.get(i);
			if (activity.getClass().isAssignableFrom(actCls)) {
				for (Activity a:buf) {
					a.finish();
				}
			}
			else if(i==size-1&&isIncludeSelf) {
				buf.add(activity);
			} 
			else if (i!=size-1) {
				buf.add(activity);
			}
		}
		return false;
	}
}
