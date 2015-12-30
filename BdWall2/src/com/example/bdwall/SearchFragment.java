package com.example.bdwall;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Random;

import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.AbsoluteLayout;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.TextView;

public class SearchFragment extends Fragment{

	
	ArrayList<ArrayList<String>> data =new ArrayList<ArrayList<String>>();
	AbsoluteLayout mylayout;
	AbsoluteLayout mylayoutTwo;
	int indexPage=0;
	int screenWidth;
	
	
	GestureDetector gestureDetector;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		    View view=inflater.inflate(R.layout.search_fragment,container,false);
		    findView(view);
	        return  view;
	}
	
	private void findView(View view) {
		
		view.setLongClickable(true);
		view.setOnTouchListener(new MyOnTouchListener());
		mylayout=(AbsoluteLayout) view.findViewById(R.id.myabsolutelayout);
		mylayoutTwo=(AbsoluteLayout) view.findViewById(R.id.myabsolutelayoutTwo);
		
		
	}
	//view �Ĵ����¼�
	private class MyOnTouchListener implements OnTouchListener{

		@Override
		public boolean onTouch(View view, MotionEvent event) {
			
			return gestureDetector.onTouchEvent(event);//�������ݸ�����
		}
		
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		init();
	}

	private void init() {
		gestureDetector=new GestureDetector(getActivity(), new MyGestureListener());
		
		
		
		DisplayMetrics metrics=new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
		screenWidth=metrics.widthPixels;
		
		
		initData();
		setDataToPage(mylayout,indexPage);
		
	}
	
	//�����¼�
  private class MyGestureListener implements OnGestureListener, android.view.GestureDetector.OnGestureListener{

	  //����
	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}
    //����  e1��ָ���µ��¼� e2��ָ̧����¼�  moveX��λ�¼�����x���Ϲ����ľ���  moveY��λʱ������Y���Ϲ����ľ���
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float moveX,
			float moveY) {
		if(e1.getX()<e2.getX()){// ���һ���
			indexPage++;
			if (indexPage==data.size()) { //Խ��
				indexPage=0;
			}
			switch (mylayout.getVisibility()) {
			case 0://mylayout��ʾ״̬   �Ŵ���ʧ      mylayoutTwo�Ŵ���ʾ
				setState(100);
				setState(101);
				break;
			case 4://����״̬��ռ����λ��
				break;
			case 8://����״̬����û��ռ��λ��
				setState(200);
				setState(201);
				break;
			
			}
		}
		return false;
	}
	
	//ˢ��UI
	Handler	handler=new Handler(){
		public void handleMessage(Message msg){
			switch (msg.what) {
			case 100:
				//myLayout �Ŵ�
					scaleOut(mylayout,1000,0);
					//mylayout ��ʧ
					mylayout.setVisibility(8);
				break;
			case 101:
				setDataToPage(mylayoutTwo, indexPage);
				//mylayoutTwo�Ŵ�
				scaleIn(mylayoutTwo,2000,0);
				//mylayoutTwo��ʾ
				 mylayoutTwo.setVisibility(0);
				break;
			case 200:
				//myLayoutTwo �Ŵ�
					scaleOut(mylayoutTwo,1000,0);
					//myLayoutTwo ��ʧ
					mylayoutTwo.setVisibility(8);
				break;
			case 201:
				setDataToPage(mylayout, indexPage);
				//myLayoutTwo �Ŵ�
				scaleIn(mylayout,2000,0);
				//myLayoutTwo ��ʾ
				mylayout.setVisibility(0);
				break;
				
			default:
				break;
			}
		}

		
		
		
	};
	//�Ŵ���ʾ
private void scaleIn(AbsoluteLayout mylayout, int time, int delay) {
	//�Ŵ�
			ScaleAnimation animation=new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0.5f);
			animation.setStartOffset(delay);
			animation.setDuration(time);
			
			//opacity 0->1
			AlphaAnimation alphaAnimation=new AlphaAnimation(0.0f, 1.0f);
			alphaAnimation.setDuration(time);
			
			AnimationSet animationSet=new AnimationSet(true);
			animationSet.addAnimation(animation);
			animationSet.addAnimation(alphaAnimation);
			
			mylayout.startAnimation(animationSet);
		
	}

	//�Ŵ���ʧ����
	private void scaleOut(AbsoluteLayout mylayout, int time, int delay) {
		//�Ŵ�
		ScaleAnimation animation=new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f,Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0.5f);
		animation.setStartOffset(delay);
		animation.setDuration(time);
		
		//opacity 1->0
		AlphaAnimation alphaAnimation=new AlphaAnimation(1.0f, 0.0f);
		alphaAnimation.setDuration(time);
		
		AnimationSet animationSet=new AnimationSet(true);
		animationSet.addAnimation(animation);
		animationSet.addAnimation(alphaAnimation);
		
		mylayout.startAnimation(animationSet);
		
		
	}
	//��̬�ı�״̬
    private void setState(final int i) {
		new Thread(new Runnable() {
				 
			@Override
			public void run() {
				 handler.sendEmptyMessage(i);
				
			}
		}).start();
		
	}
	//���� 
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
//����
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	//��ʱ�䰴��Ļ���ɿ�������onLongPressҲ�ᴥ�������
	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	//����
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onGesture(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGestureCancelled(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	  
  }
	
	
	//�������������
	private void initData() {
		for (int i = 0; i <2; i++) {
			switch (i) {
			case 0:
				ArrayList<String> newdata=new ArrayList<String>();
				for (int j = 0; j < 15; j++) {
					newdata.add("ƻ��"+j);
				}
				data.add(newdata);
				break;

			case 1:
				ArrayList<String> newdata1=new ArrayList<String>();
				for (int j = 0; j < 15; j++) {
					newdata1.add("����"+j);
				}
				data.add(newdata1);
				break;
			}
		}
		
	}
	//��ҳ���������
	private void setDataToPage(AbsoluteLayout mylayout, int indexPage) {
		mylayout.removeAllViewsInLayout();
		int startY=50;
		for(int i=0;i<data.get(indexPage).size();i++){
			int x=get(50,screenWidth-50);
			int y=startY;
			TextView tv=new TextView(getActivity());
			tv.setTextSize(16);
			tv.setText(data.get(indexPage).get(i));
			AbsoluteLayout.LayoutParams layoutParams=new LayoutParams(150, 60,x, y);
			
			mylayout.addView(tv,layoutParams);
			startY+=50;
			
			
		}
		
	}

	//�������min~max֮�������
	private int get(int min, int max) {
		
		return new Random().nextInt(max)%(max-min+1)+min;
	}

}
